package yz.doodlejump.service;

import yz.doodlejump.core.SessionManager;
import yz.doodlejump.entity.DAO.PlayerDAO;
import yz.doodlejump.entity.bean.Player;
import yz.doodlejump.entity.bean.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 认证服务
 */
@Path("auth")
public class Auth {

    @Path("hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello World!";
    }

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
            Player player = new Player(name, bio, email, password);
            return PlayerDAO.add(player);
        }
    }

    @Path("signin")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String signIn(@FormParam("name") final String name,
                         @FormParam("password") final String password) {
        //判定玩家是否已持有会话
        if (!SessionManager.isPlayerHoldSessionByName(name)){
            //未持有
            //判定玩家用户名是否与密码匹配
            if (PlayerDAO.checkNameAndPassword(name, password)) {
                //匹配
                Player player = PlayerDAO.getByName(name);
                //返回分配的会话
                return SessionManager.create(player).getId();
            } else {
                //不匹配
                return "1";
            }
        } else {
            //已持有
            return "2";
        }
    }

    @Path("info")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Player info(@QueryParam("session") final String sessionId) {
        //判定会话是否合法
        if (SessionManager.isSessionValid(sessionId)) {
            //合法
            int userId = SessionManager.getPlayerIdBySessionId(sessionId);
            return PlayerDAO.getById(userId);
        } else {
            //不合法
            return null;
        }
    }

    @Path("signout")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public int signOut(@QueryParam("session") final String sessionId) {
        SessionManager.invalidate(sessionId);
        return 0;
    }
}
