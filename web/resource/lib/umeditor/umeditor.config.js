
etpl.config({
    commandOpen: '<%',
    commandClose: '%>'
});

(function () {
    var URL = window.UMEDITOR_HOME_URL || (function(){

        function PathStack() {

            this.documentURL = self.document.URL || self.location.href;

            this.separator = '/';
            this.separatorPattern = /\\|\//g;
            this.currentDir = './';
            this.currentDirPattern = /^[.]\/]/;

            this.path = this.documentURL;
            this.stack = [];

            this.push( this.documentURL );

        }

        PathStack.isParentPath = function( path ){
            return path === '..';
        };

        PathStack.hasProtocol = function( path ){
            return !!PathStack.getProtocol( path );
        };

        PathStack.getProtocol = function( path ){

            var protocol = /^[^:]*:\/*/.exec( path );

            return protocol ? protocol[0] : null;

        };

        PathStack.prototype = {
            push: function( path ){

                this.path = path;

                update.call( this );
                parse.call( this );

                return this;

            },
            getPath: function(){
                return this + "";
            },
            toString: function(){
                return this.protocol + ( this.stack.concat( [''] ) ).join( this.separator );
            }
        };

        function update() {

            var protocol = PathStack.getProtocol( this.path || '' );

            if( protocol ) {

                //根协议
                this.protocol = protocol;

                //local
                this.localSeparator = /\\|\//.exec( this.path.replace( protocol, '' ) )[0];

                this.stack = [];
            } else {
                protocol = /\\|\//.exec( this.path );
                protocol && (this.localSeparator = protocol[0]);
            }

        }

        function parse(){

            var parsedStack = this.path.replace( this.currentDirPattern, '' );

            if( PathStack.hasProtocol( this.path ) ) {
                parsedStack = parsedStack.replace( this.protocol , '');
            }

            parsedStack = parsedStack.split( this.localSeparator );
            parsedStack.length = parsedStack.length - 1;

            for(var i= 0,tempPath,l=parsedStack.length,root = this.stack;i<l;i++){
                tempPath = parsedStack[i];
                if(tempPath){
                    if( PathStack.isParentPath( tempPath ) ) {
                        root.pop();
                    } else {
                        root.push( tempPath );
                    }
                }

            }


        }

        var currentPath = document.getElementsByTagName('script');

        currentPath = currentPath[ currentPath.length -1 ].src;

        return new PathStack().push( currentPath ) + "";


    })();
    URL = "/resource/lib/umeditor/"
    /**
     * 配置项主体。注意，此处所有涉及到路径的配置别遗漏URL变量。
     */
    window.UMEDITOR_CONFIG = {

        //为编辑器实例添加一个路径，这个不能被注释
        UMEDITOR_HOME_URL : URL

        //图片上传配置区
        ,imageUrl:"/api/admin/upload"             //图片上传提交地址
        ,imagePath:"http://localhost"                     //图片修正地址，引用了fixedImagePath,如有特殊需求，可自行配置
        ,imageFieldName:"upfile"                   //图片数据的key,若此处修改，需要在后台对应文件修改对应参数

        //工具栏上的所有的功能按钮和下拉框，可以在new编辑器的实例时选择自己需要的从新定义
        ,toolbar:[
            'source | undo redo | bold italic underline strikethrough | superscript subscript | forecolor backcolor | removeformat |',
            'insertorderedlist insertunorderedlist | selectall cleardoc formula | fontfamily fontsize' ,
            '| justifyleft justifycenter justifyright justifyjustify |',
            'link unlink | emotion image video'
        ]
        //针对getAllHtml方法，会在对应的head标签中增加该编码设置。
        //,charset:"utf-8"
        //,initialFrameWidth:500 //初始化编辑器宽度,默认500
        ,initialFrameHeight:200  //初始化编辑器高度,默认500
        //,emotionLocalization:false //是否开启表情本地化，默认关闭。若要开启请确保emotion文件夹下包含官网提供的images表情文件夹
        ,'fontsize':[10, 11, 12, 14, 16, 18, 20, 24, 36]
        ,filterRules: {}
        // xss 过滤是否开启,inserthtml等操作
 		,xssFilterRules: true
 		//input xss过滤
 		,inputXssFilter: true
 		//output xss过滤
 		,outputXssFilter: true
 		// xss过滤白名单 名单来源: https://raw.githubusercontent.com/leizongmin/js-xss/master/lib/default.js
        ,whiteList: {
            a:      ['target', 'href', 'title', 'style', 'class', 'id'],
			abbr:   ['title', 'style', 'class', 'id'],
			address: ['style', 'class', 'id'],
			area:   ['shape', 'coords', 'href', 'alt', 'style', 'class', 'id'],
			article: ['style', 'class', 'id'],
			aside:  ['style', 'class', 'id'],
			audio:  ['autoplay', 'controls', 'loop', 'preload', 'src', 'style', 'class', 'id'],
			b:      ['style', 'class', 'id'],
			bdi:    ['dir'],
			bdo:    ['dir'],
			big:    [],
			blockquote: ['cite', 'style', 'class', 'id'],
			br:     [],
			caption: ['style', 'class', 'id'],
			center: [],
			cite:   [],
			code:   ['style', 'class', 'id'],
			col:    ['align', 'valign', 'span', 'width', 'style', 'class', 'id'],
			colgroup: ['align', 'valign', 'span', 'width', 'style', 'class', 'id'],
			dd:     ['style', 'class', 'id'],
			del:    ['datetime', 'style', 'class', 'id'],
			details: ['open', 'style', 'class', 'id'],
			div:    ['style', 'class', 'id'],
			dl:     ['style', 'class', 'id'],
			dt:     ['style', 'class', 'id'],
			em:     ['style', 'class', 'id'],
            embed:  ['style', 'class', 'id', '_url', 'type', 'pluginspage', 'src', 'width', 'height', 'wmode', 'play', 'loop', 'menu', 'allowscriptaccess', 'allowfullscreen'],
			font:   ['color', 'size', 'face', 'style', 'class', 'id'],
			footer: ['style', 'class', 'id'],
			h1:     ['style', 'class', 'id'],
			h2:     ['style', 'class', 'id'],
			h3:     ['style', 'class', 'id'],
			h4:     ['style', 'class', 'id'],
			h5:     ['style', 'class', 'id'],
			h6:     ['style', 'class', 'id'],
			header: ['style', 'class', 'id'],
			hr:     ['style', 'class', 'id'],
			i:      ['style', 'class', 'id'],
            iframe: ['style', 'class', 'id', 'src', 'frameborder', 'data-latex'],
			img:    ['src', 'alt', 'title', 'width', 'height', 'style', 'class', 'id', '_url'],
			ins:    ['datetime', 'style', 'class', 'id'],
			li:     ['style', 'class', 'id'],
			mark:   [],
			nav:    [],
			ol:     ['style', 'class', 'id'],
			p:      ['style', 'class', 'id'],
			pre:    ['style', 'class', 'id'],
			s:      [],
			section:[],
			small:  ['style', 'class', 'id'],
			span:   ['style', 'class', 'id'],
			sub:    ['style', 'class', 'id'],
			sup:    ['style', 'class', 'id'],
			strong: ['style', 'class', 'id'],
			table:  ['width', 'border', 'align', 'valign', 'style', 'class', 'id'],
			tbody:  ['align', 'valign', 'style', 'class', 'id'],
			td:     ['width', 'rowspan', 'colspan', 'align', 'valign', 'style', 'class', 'id'],
			tfoot:  ['align', 'valign', 'style', 'class', 'id'],
			th:     ['width', 'rowspan', 'colspan', 'align', 'valign', 'style', 'class', 'id'],
			thead:  ['align', 'valign', 'style', 'class', 'id'],
			tr:     ['rowspan', 'align', 'valign', 'style', 'class', 'id'],
			tt:     ['style', 'class', 'id'],
			u:      [],
			ul:     ['style', 'class', 'id'],
            svg:    ['style', 'class', 'id', 'width', 'height', 'xmlns', 'fill', 'viewBox'],
			video:  ['autoplay', 'controls', 'loop', 'preload', 'src', 'height', 'width', 'style', 'class', 'id']
        }
    };
})();
