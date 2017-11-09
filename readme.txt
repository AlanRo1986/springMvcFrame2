整合的spring mvc框架,全java配置
{
    开发工具:IntelliJ IDEA 2017.1.5
    SpringMVC版本:4.3.10
    hibernate-validator:5.2.5.Final
    mysql-connector:6.0.6
}

框架操作例子:
index.jsp

长连接例子:
1.html

##表单验证:
    @Autowired
    private ValidatorProvider<Article> validatorProvider;
    validatorProvider.validate(Object);

##消息推送:
    @Autowired
    private ApplicationEventPublisher publisher;
    publisher.publishEvent(new ApplicationWebSocketEvent(new TextMessagePojo()));

    @Autowired
    private IRedisMessageService messageService;
    messageService.sendMessage(ConstantRedis.CHANNEL,JsonUtil.ObjectToJson(textMessagePojo));

##本地化(默认:Constant.defaultLocale:zh_CN)
url可以传递:locale=en_US
locale:key
en_US:val(对应的语言(lang)文件名的前缀)

默认会获取http请求过来的locale值,如果没有则读取:
request.getLocale().getLanguage() + "_" + request.getLocale().getCountry();

Application.getInstance(null).getLang(Lang String);

##控制器
所有的控制器应该遵守IDefaultControllerMethod接口定义,并实现restful标准的方法准则;

#2017-11-09增加Redis pub&sub的接口类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\config\RedisMessageListenerConfiguration.java //#消息队列配置
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\listener\RedisMessageListener.java #消息监听
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\service\impl\RedisMessageService.java #消息发送逻辑类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\constant\ConstantRedis.java #redis配置文件

#系统核心启动文件
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\Bootstrap.java

D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\app\controller\ #控制器的目录
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\app\view\ #页面输入的控制器目录
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\config\ #系统配置文件
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\config\HibernateConfiguration.java #hibernate数据库配置
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\config\MybatisConfiguration.java #mybatis数据库配置
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\config\SocketConfiguration.java #socket配置

D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\constant\Constant.java #系统常量
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\constant\ConstantAuthority.java #鉴权配置文件
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\constant\ConstantInit.java #数据库配置文件
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\constant\ConstantRedis.java #Redis配置文件

D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\lang\zh_CN\errors.properties #语言文件->错误类 Application.getInstance(null).getLang(request.getParameter(key),null);
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\lang\zh_CN\lang.properties #语言文件->信息类

#模型目录(没有使用)
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\model\

#hibernate操作类实体类服务类仓库类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\basic\AbstractDefaultBaseRepository.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\basic\AbstractDefaultRepository.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\basic\Criteria.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\basic\DefaultBaseRepository.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\basic\DefaultRepository.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\basic\Delete.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\basic\Gender.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\basic\Status.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\basic\UserLogType.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\dao\ArticleRepository.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\dao\CommentRepository.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\dao\UserLogRepository.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\dao\UserRepository.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\dao\UserTokenRepository.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\pojo\Article.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\pojo\Comment.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\pojo\User.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\pojo\UserLog.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\pojo\UserToken.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\resource\IArticleRepository.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\resource\ICommentRepository.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\resource\IDefaultRepository.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\resource\IUserLogRepository.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\resource\IUserRepository.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\resource\IUserTokenRepository.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\service\impl\ArticleServiceImpl2.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\service\impl\CommentServiceImpl2.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\service\impl\LoginServiceImpl2.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\service\impl\UserLogServiceImpl2.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\service\impl\UserServiceImpl2.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\service\impl\UserTokenServiceImpl2.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\service\resource\IArticleService2.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\service\resource\ICommentService2.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\service\resource\ILoginService2.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\service\resource\IUserLogService2.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\service\resource\IUserService2.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\hibernate\service\resource\IUserTokenService2.java

#mybatis仓库类跟xml文件
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\mybatis\mapper\UserMapper.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\repository\mybatis\resource\UserMapper.xml

#mybatis服务类文件
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\service\impl\TestServiceImpl.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\service\impl\UploadServiceImpl.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\service\impl\UserServiceImpl.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\service\resource\ITestService.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\service\resource\IUploadService.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\service\resource\IUserService.java

#socket文件
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\socket\MessageWebSocketHandler.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\socket\WebSocketHandshakeInterceptor.java

#自定义注解
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\annotation\Age.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\annotation\ArticleContent.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\annotation\EventBus.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\annotation\Mobile.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\annotation\Password.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\annotation\PivotalId.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\annotation\Provider.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\annotation\RepositoryHibernate.java #切换到mybatis职需要过滤此注解扫描即可
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\annotation\Rest2Controller.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\annotation\ServiceHibernate.java #切换到mybatis职需要过滤此注解扫描即可

#系统核心基础类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\basic\BasicApiController.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\basic\BasicProvider.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\basic\BasicServiceImpl.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\basic\Compact.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\basic\CompactController.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\basic\CompactProvider.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\basic\CompactService.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\basic\ExceptionError.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\basic\IComponent.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\basic\IDefaultControllerMethod.java

#事件监听类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\bus\ApplicationUserEventListener.java #文章发布监听类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\bus\ArticleCommentEventListener.java #文章评论监听类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\bus\LoginEventListener.java #用户登录消息监听类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\bus\LoginOutEventListener.java #用户注销消息监听类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\bus\UserLogEventListener.java #用户日志消息监听类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\bus\UserTokenEventListener.java #用户token消息监听类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\bus\WebSocketEventListener.java #socket消息监听类

#事件类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\bus\event\ApplicationArticleEvent.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\bus\event\ApplicationUserEvent.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\bus\event\ApplicationWebSocketEvent.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\bus\event\ArticleCommentEvent.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\bus\event\LoginEvent.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\bus\event\LoginOutEvent.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\bus\event\UserLogEvent.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\bus\event\UserTokenEvent.java

#自定义callback接口类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\callback\IFilePutCallback.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\callback\IRedisCallback.java

#系统核心类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\core\Application.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\core\ApplicationContextListener.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\core\ApplicationHandlerInterceptor.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\core\CrossDomainFilter.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\core\ResultResp.java

#异常类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\exception\IllegalAccessDeniedException.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\exception\IllegalComponentException.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\exception\IllegalHttpException.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\exception\IllegalIOException.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\exception\IllegalServiceException.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\exception\IllegalValidateException.java

#session跟request请求监听类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\listener\RequestListener.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\listener\SessionListener.java

#系统简单对象实体类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\pojo\AuthorityPojo.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\pojo\PagePojo.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\pojo\PredicatePojo.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\pojo\SocketTextMessagePojo.java

#提供者对象
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\CacheProvider.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\FileProvider.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\HttpProvider.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\ScheduleProvider.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\SecurityProvider.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\SessionProvider.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\TestProvider.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\ValidatorProvider.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\VerifyProvider.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\basic\DCacheData.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\basic\HttpRequestData.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\basic\ICache.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\basic\IRequest.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\basic\ISecurity.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\basic\ISession.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\basic\IStoreFile.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\handler\CacheMemHandler.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\handler\CacheRedisHandler.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\handler\FileHandler.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\provider\handler\HttpConnector.java

#工具类
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\utils\CommonUtil.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\utils\DateUtil.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\utils\JsonUtil.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\utils\Md5Util.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\utils\NumberUtil.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\utils\SerializeUtil.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\validation\ArticleContentValidation.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\validation\MobileValidation.java
D:\develop\work\IdeaProjects\mymvc\src\com\mymvc\system\validation\PivotalIdValidation.java


