
/**
 * app对象类
 * @returns {{bind: bind, event: event, ajax: ajax, session: session, cookie: cookie, log: log}}
 */
var app = {
    /**
     * 常量
     */
    constant:{
        CACHE_TIME:10,//缓存时间，单位“秒”
        NO_CACHE:["test"],//排除缓存的控制器
    },
    /**
     * 绑定数据
     * @param id 需要绑定数据的id
     * @param val
     */
    bindText: function (id, val) {
        $(id).text(val);
    },
    /**
     * 绑定input的值
     * @param id
     * @param val
     */
    bindVal: function (id, val) {
        $(id).val(val);
    },
    /**
     * 元素事件绑定
     * @param obj 元素对象，如："#button"
     * @param type 事件类型，参考jquery事件类型，如："click"
     * @param fn 事件处理函数
     */
    event: function (obj, type, fn) {
        $(obj).on(type, fn);
    },
    getRequest:function (key) {
        var reg = new RegExp('(^|&)' + key + '=([^&]*)(&|$)', 'i');
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    },
    /**
     * 发起ajax请求
     * @param url 请求地址
     * @param data 请求参数（object）对象
     * @param fnScuss 成功回调的函数
     * @param fnError 失败回调的函数
     * @returns {{get: get, post: post}}
     */
    ajax: function (url, data, fnScuss, fnError) {

        $(".loading").show();
        var _cacheKey = app.md5(url+JSON.stringify(data));
        var _cache = null;
        var _isCanCache = app.isCanCache(url);

        var http = function (method,header) {
            if (method == null){
                method = "POST";
            }
            if(header == null){
                header = new Object();
            }
            header.token = app.local.get("token");
            header.version = "1.0.0";
            $.ajax({
                url: url,
                data: data,
                dataType: "json",
                type: method,
                headers: header,
                success: function (e) {
                    if((e.code >= 401 && e.code <=403) || (e.code >= 4010 && e.code <= 4039)){
                        app.local.del("token");
                    }

                    if (typeof fnScuss == "function") {
                        //储存缓存
                        if (e.code == "1" && _isCanCache == true){
                            //app.cache.set(_cacheKey,e)
                        }
                        fnScuss(e);
                    }
                    $(".loading").hide();
                },
                error: function (e) {
                    if (typeof fnError == "function") {
                        fnError(e);
                    }
                    $(".loading").hide();
                }

            })
        };

        return {
            get: function () {
                //缓存判断
                if (_isCanCache == true){
                    _cache = app.cache.get(_cacheKey)
                    if (_cache != null && typeof fnScuss == "function"){
                        fnScuss(_cache);
                        return true;
                    }
                }

                http("GET");
            },
            post: function (isJson) {
                //缓存判断
                if (_isCanCache == true){
                    _cache = app.cache.get(_cacheKey)
                    if (_cache != null && typeof fnScuss == "function"){
                        fnScuss(_cache);
                        return true;
                    }
                }
                var header = new Object();
                if (isJson == true){
                    header['Content-Type'] = "application/json";
                }
                http("POST",header);
            },
            delete:function () {
                http("DELETE");
            },
            put:function (isJson) {
                var header = new Object();
                if (isJson == true){
                    header['Content-Type'] = "application/json";
                }
                http("PUT",header);

            }
        }

    },
    /**
     * 判断是否需要缓存
     * @param url
     * @returns {boolean}
     */
    isCanCache:function (url) {
        var isCanCache = true;
        for(var i in app.constant.NO_CACHE){
            app.log(app.constant.NO_CACHE[i]);
            if (url.indexOf(app.constant.NO_CACHE[i]) != -1){
                isCanCache = false;
                break;
            }
        }
        return isCanCache;
    },
    /**
     * session本地数据库储存
     * @returns {{set: set, get: get, del: del}}
     */
    session: {
        set: function (key, val) {
            if (key.length <= 0) {
                alert("Session key must be require.");
                return false;
            }

            sessionStorage.setItem(key, val);
            return true;

        },
        get: function (key) {
            return sessionStorage.getItem(key);
        },
        del: function (key) {
            sessionStorage.removeItem(key);
            return true;
        }

    },
    local: {
        set: function (key, val) {
            if (key.length <= 0) {
                alert("Session key must be require.");
                return false;
            }

            localStorage.setItem(key, val);
            return true;

        },
        get: function (key) {
            return localStorage.getItem(key);
        },
        del: function (key) {
            localStorage.removeItem(key);
            return true;
        }

    },
    /**
     * cookie数据储存
     * @returns {{set: set, get: get, del: del}}
     */
    cookie: {
        set: function (key, val, expires, path, domain, secure) {
            if (key.length <= 0) {
                alert("Cookie key must be require.");
                return false;
            }

            var cookieText = encodeURIComponent(key) + "=" + encodeURIComponent(val);

            if (expires instanceof Date) {
                cookieText += "; expires=" + expires.toUTCString();
            }
            if (path) {
                cookieText += "; path=" + path;
            }
            if (domain) {
                cookieText += "; domain=" + domain;
            }
            if (secure) {
                cookieText += "; " + secure;
            }

            document.cookie = cookieText;
            return true;
        },
        get: function (key) {
            var cookieText = encodeURIComponent(key) + "=";
            var cookieValue = "";
            var start = document.cookie.indexOf(cookieText);

            if (start > -1) {
                var end = document.cookie.indexOf(";", start);
                if (end == -1) {
                    end = document.cookie.length;
                }
                cookieValue = encodeURIComponent(document.cookie.substring(start + cookieText.length, end));
            }
            return cookieValue;

        },
        del: function (key) {
            this.set(key, "", new Date(0));
        }

    },
    /**
     * 缓存储存对象
     */
    cache: {
        get:function (key) {
            if (key != null && key.length == 32){
                var __data = app.session.get(key);
                if(typeof __data == "string"){
                    __data = JSON.parse(__data);
                    if(__data instanceof Object && typeof __data.__expiratTime == "number" == true){
                        if(__data.__expiratTime > new Date().getTime()){
                            delete __data.__expiratTime;
                            return __data;
                        }
                    }
                }
                app.session.del(key);
            }
            return null;
        },
        set:function (key,val) {
            if (key != null && key.length == 32 && val instanceof Object == true){
                val.__expiratTime = new Date().getTime() + app.constant.CACHE_TIME * 1000;
                app.session.set(key,JSON.stringify(val))
            }
        },
        remove:function (key) {
            app.session.del(key);
        }
    },
    /**
     * MD5加密对象
     * @param string
     * @returns {*}
     */
    md5:function(string) {

        function safe_add(x, y) {
            var lsw = (x & 0xFFFF) + (y & 0xFFFF),
                msw = (x >> 16) + (y >> 16) + (lsw >> 16);
            return (msw << 16) | (lsw & 0xFFFF);
        }

        /*
         * Bitwise rotate a 32-bit number to the left.
         */
        function bit_rol(num, cnt) {
            return (num << cnt) | (num >>> (32 - cnt));
        }

        /*
         * These functions implement the four basic operations the algorithm uses.
         */
        function md5_cmn(q, a, b, x, s, t) {
            return safe_add(bit_rol(safe_add(safe_add(a, q), safe_add(x, t)), s), b);
        }

        function md5_ff(a, b, c, d, x, s, t) {
            return md5_cmn((b & c) | ((~b) & d), a, b, x, s, t);
        }

        function md5_gg(a, b, c, d, x, s, t) {
            return md5_cmn((b & d) | (c & (~d)), a, b, x, s, t);
        }

        function md5_hh(a, b, c, d, x, s, t) {
            return md5_cmn(b ^ c ^ d, a, b, x, s, t);
        }

        function md5_ii(a, b, c, d, x, s, t) {
            return md5_cmn(c ^ (b | (~d)), a, b, x, s, t);
        }

        /*
         * Calculate the MD5 of an array of little-endian words, and a bit length.
         */
        function binl_md5(x, len) {
            /* append padding */
            x[len >> 5] |= 0x80 << (len % 32);
            x[(((len + 64) >>> 9) << 4) + 14] = len;

            var i, olda, oldb, oldc, oldd,
                a = 1732584193,
                b = -271733879,
                c = -1732584194,
                d = 271733878;

            for (i = 0; i < x.length; i += 16) {
                olda = a;
                oldb = b;
                oldc = c;
                oldd = d;

                a = md5_ff(a, b, c, d, x[i], 7, -680876936);
                d = md5_ff(d, a, b, c, x[i + 1], 12, -389564586);
                c = md5_ff(c, d, a, b, x[i + 2], 17, 606105819);
                b = md5_ff(b, c, d, a, x[i + 3], 22, -1044525330);
                a = md5_ff(a, b, c, d, x[i + 4], 7, -176418897);
                d = md5_ff(d, a, b, c, x[i + 5], 12, 1200080426);
                c = md5_ff(c, d, a, b, x[i + 6], 17, -1473231341);
                b = md5_ff(b, c, d, a, x[i + 7], 22, -45705983);
                a = md5_ff(a, b, c, d, x[i + 8], 7, 1770035416);
                d = md5_ff(d, a, b, c, x[i + 9], 12, -1958414417);
                c = md5_ff(c, d, a, b, x[i + 10], 17, -42063);
                b = md5_ff(b, c, d, a, x[i + 11], 22, -1990404162);
                a = md5_ff(a, b, c, d, x[i + 12], 7, 1804603682);
                d = md5_ff(d, a, b, c, x[i + 13], 12, -40341101);
                c = md5_ff(c, d, a, b, x[i + 14], 17, -1502002290);
                b = md5_ff(b, c, d, a, x[i + 15], 22, 1236535329);

                a = md5_gg(a, b, c, d, x[i + 1], 5, -165796510);
                d = md5_gg(d, a, b, c, x[i + 6], 9, -1069501632);
                c = md5_gg(c, d, a, b, x[i + 11], 14, 643717713);
                b = md5_gg(b, c, d, a, x[i], 20, -373897302);
                a = md5_gg(a, b, c, d, x[i + 5], 5, -701558691);
                d = md5_gg(d, a, b, c, x[i + 10], 9, 38016083);
                c = md5_gg(c, d, a, b, x[i + 15], 14, -660478335);
                b = md5_gg(b, c, d, a, x[i + 4], 20, -405537848);
                a = md5_gg(a, b, c, d, x[i + 9], 5, 568446438);
                d = md5_gg(d, a, b, c, x[i + 14], 9, -1019803690);
                c = md5_gg(c, d, a, b, x[i + 3], 14, -187363961);
                b = md5_gg(b, c, d, a, x[i + 8], 20, 1163531501);
                a = md5_gg(a, b, c, d, x[i + 13], 5, -1444681467);
                d = md5_gg(d, a, b, c, x[i + 2], 9, -51403784);
                c = md5_gg(c, d, a, b, x[i + 7], 14, 1735328473);
                b = md5_gg(b, c, d, a, x[i + 12], 20, -1926607734);

                a = md5_hh(a, b, c, d, x[i + 5], 4, -378558);
                d = md5_hh(d, a, b, c, x[i + 8], 11, -2022574463);
                c = md5_hh(c, d, a, b, x[i + 11], 16, 1839030562);
                b = md5_hh(b, c, d, a, x[i + 14], 23, -35309556);
                a = md5_hh(a, b, c, d, x[i + 1], 4, -1530992060);
                d = md5_hh(d, a, b, c, x[i + 4], 11, 1272893353);
                c = md5_hh(c, d, a, b, x[i + 7], 16, -155497632);
                b = md5_hh(b, c, d, a, x[i + 10], 23, -1094730640);
                a = md5_hh(a, b, c, d, x[i + 13], 4, 681279174);
                d = md5_hh(d, a, b, c, x[i], 11, -358537222);
                c = md5_hh(c, d, a, b, x[i + 3], 16, -722521979);
                b = md5_hh(b, c, d, a, x[i + 6], 23, 76029189);
                a = md5_hh(a, b, c, d, x[i + 9], 4, -640364487);
                d = md5_hh(d, a, b, c, x[i + 12], 11, -421815835);
                c = md5_hh(c, d, a, b, x[i + 15], 16, 530742520);
                b = md5_hh(b, c, d, a, x[i + 2], 23, -995338651);

                a = md5_ii(a, b, c, d, x[i], 6, -198630844);
                d = md5_ii(d, a, b, c, x[i + 7], 10, 1126891415);
                c = md5_ii(c, d, a, b, x[i + 14], 15, -1416354905);
                b = md5_ii(b, c, d, a, x[i + 5], 21, -57434055);
                a = md5_ii(a, b, c, d, x[i + 12], 6, 1700485571);
                d = md5_ii(d, a, b, c, x[i + 3], 10, -1894986606);
                c = md5_ii(c, d, a, b, x[i + 10], 15, -1051523);
                b = md5_ii(b, c, d, a, x[i + 1], 21, -2054922799);
                a = md5_ii(a, b, c, d, x[i + 8], 6, 1873313359);
                d = md5_ii(d, a, b, c, x[i + 15], 10, -30611744);
                c = md5_ii(c, d, a, b, x[i + 6], 15, -1560198380);
                b = md5_ii(b, c, d, a, x[i + 13], 21, 1309151649);
                a = md5_ii(a, b, c, d, x[i + 4], 6, -145523070);
                d = md5_ii(d, a, b, c, x[i + 11], 10, -1120210379);
                c = md5_ii(c, d, a, b, x[i + 2], 15, 718787259);
                b = md5_ii(b, c, d, a, x[i + 9], 21, -343485551);

                a = safe_add(a, olda);
                b = safe_add(b, oldb);
                c = safe_add(c, oldc);
                d = safe_add(d, oldd);
            }
            return [a, b, c, d];
        }

        /*
         * Convert an array of little-endian words to a string
         */
        function binl2rstr(input) {
            var i,
                output = '';
            for (i = 0; i < input.length * 32; i += 8) {
                output += String.fromCharCode((input[i >> 5] >>> (i % 32)) & 0xFF);
            }
            return output;
        }

        /*
         * Convert a raw string to an array of little-endian words
         * Characters >255 have their high-byte silently ignored.
         */
        function rstr2binl(input) {
            var i,
                output = [];
            output[(input.length >> 2) - 1] = undefined;
            for (i = 0; i < output.length; i += 1) {
                output[i] = 0;
            }
            for (i = 0; i < input.length * 8; i += 8) {
                output[i >> 5] |= (input.charCodeAt(i / 8) & 0xFF) << (i % 32);
            }
            return output;
        }

        /*
         * Calculate the MD5 of a raw string
         */
        function rstr_md5(s) {
            return binl2rstr(binl_md5(rstr2binl(s), s.length * 8));
        }

        /*
         * Calculate the HMAC-MD5, of a key and some data (raw strings)
         */
        function rstr_hmac_md5(key, data) {
            var i,
                bkey = rstr2binl(key),
                ipad = [],
                opad = [],
                hash;
            ipad[15] = opad[15] = undefined;
            if (bkey.length > 16) {
                bkey = binl_md5(bkey, key.length * 8);
            }
            for (i = 0; i < 16; i += 1) {
                ipad[i] = bkey[i] ^ 0x36363636;
                opad[i] = bkey[i] ^ 0x5C5C5C5C;
            }
            hash = binl_md5(ipad.concat(rstr2binl(data)), 512 + data.length * 8);
            return binl2rstr(binl_md5(opad.concat(hash), 512 + 128));
        }

        /*
         * Convert a raw string to a hex string
         */
        function rstr2hex(input) {
            var hex_tab = '0123456789abcdef',
                output = '',
                x,
                i;
            for (i = 0; i < input.length; i += 1) {
                x = input.charCodeAt(i);
                output += hex_tab.charAt((x >>> 4) & 0x0F) +
                    hex_tab.charAt(x & 0x0F);
            }
            return output;
        }

        /*
         * Encode a string as utf-8
         */
        function str2rstr_utf8(input) {
            return unescape(encodeURIComponent(input));
        }

        /*
         * Take string arguments and return either raw or hex encoded strings
         */
        function raw_md5(s) {
            return rstr_md5(str2rstr_utf8(s));
        }

        function hex_md5(s) {
            return rstr2hex(raw_md5(s));
        }

        function raw_hmac_md5(k, d) {
            return rstr_hmac_md5(str2rstr_utf8(k), str2rstr_utf8(d));
        }

        function hex_hmac_md5(k, d) {
            return rstr2hex(raw_hmac_md5(k, d));
        }

        function md5(string, key, raw) {
            if (!key) {
                if (!raw) {
                    return hex_md5(string);
                }
                return raw_md5(string);
            }
            if (!raw) {
                return hex_hmac_md5(key, string);
            }
            return raw_hmac_md5(key, string);
        }

        return md5(string);
    },
    log: function (obj) {
        console.info("app->", obj);
    }
};



