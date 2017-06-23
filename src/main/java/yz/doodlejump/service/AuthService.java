package yz.doodlejump.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yz.doodlejump.core.SessionManager;
import yz.doodlejump.entity.DAO.PlayerDAO;
import yz.doodlejump.entity.bean.Player;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 认证服务
 */
@Path("auth")
public class AuthService {
    //日志记录对象
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    /**
     * 注册
     *
     * @param name     玩家名
     * @param bio      个性签名
     * @param email    电邮
     * @param password 密码
     * @return 若注册成功，返回0；否则返回1
     */
    @Path("signup")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public int signUp(@FormParam("name") final String name,
                      @FormParam("bio") final String bio,
                      @FormParam("email") final String email,
                      @FormParam("password") final String password) {
        //检查是否存在该用户
        if (PlayerDAO.isExistByName(name)) {
            //存在
            return 1;
        } else {
            //不存在
            LOGGER.info("signup " + name);
            Player player = new Player(name, bio, email, password);
            return PlayerDAO.add(player);
        }
    }

    /**
     * 登入
     *
     * @param name     玩家名
     * @param password 密码
     * @return 若登录成功，返回长度为32的会话id字符串；若已经登入，返回2；若不匹配，返回1；
     */
    @Path("signin")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String signIn(@FormParam("name") final String name,
                         @FormParam("password") final String password) {

        //判定玩家是否已持有会话
        if (!SessionManager.isPlayerHoldSessionByName(name)) {
            //未持有
            //判定玩家用户名是否与密码匹配
            if (PlayerDAO.checkNameAndPassword(name, password)) {
                LOGGER.info("signin " + name);
                //匹配
                Player player = PlayerDAO.getByName(name);
                //返回分配的会话
                return SessionManager.create(player).getId();
            } else {
                //不匹配
                return "1";
            }
        } else {
            //已登入
            return "2";
        }
    }

    /**
     * 获取玩家信息
     *
     * @param sessionId 会话id
     * @return 玩家信息
     */
    @Path("info")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Player info(@QueryParam("session") final String sessionId) {
        //判定会话是否合法
        if (SessionManager.isValid(sessionId)) {
            LOGGER.info("info " + sessionId);
            //合法
            int userId = SessionManager.getPlayerIdBySessionId(sessionId);
            Player player = PlayerDAO.getById(userId);
            return player;
        } else {
            //不合法
            return null;
        }
    }

    /**
     * 登出
     *
     * @param sessionId 会话id
     * @return 登出成功，返回0；否则返回1
     */
    @Path("signout")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public int signOut(@QueryParam("session") final String sessionId) {
        if (SessionManager.isValid(sessionId)) {
            LOGGER.info("signout");
            SessionManager.invalidate(sessionId);
            return 0;
        } else {
            return 1;
        }
    }
}
