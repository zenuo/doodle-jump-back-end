package yz.doodlejump.entity.DAO;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import yz.doodlejump.core.Data;
import yz.doodlejump.core.Util;
import yz.doodlejump.entity.bean.Player;
import yz.doodlejump.entity.mapper.PlayerMapper;

/**
 * 玩家数据库访问类
 */
public class PlayerDAO {
    public static Player getById(final int id){
        try (SqlSession sqlSession = Data.getSqlSession()) {
            PlayerMapper playerMapper = sqlSession.getMapper(PlayerMapper.class);
            return playerMapper.getPlayerById(id);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Player getByName(final String name) {
        try (SqlSession sqlSession = Data.getSqlSession()) {
            PlayerMapper playerMapper = sqlSession.getMapper(PlayerMapper.class);
            return playerMapper.getPlayerByName(name);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean checkNameAndPassword(final String name, final String password) {
        try (SqlSession sqlSession = Data.getSqlSession()) {
            PlayerMapper playerMapper = sqlSession.getMapper(PlayerMapper.class);
            String hashedString = Util.hash(password);
            //这里写错成“ == 1”，应该是” == 0“
            return playerMapper.checkNameAndPassword(name, hashedString) == 0;
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int updateRecord(final int id, final int record){
        try (SqlSession sqlSession = Data.getSqlSession()) {
            if (record > getById(id).getRecord()) {
                PlayerMapper playerMapper = sqlSession.getMapper(PlayerMapper.class);
                playerMapper.updateRecord(id, record);
                sqlSession.commit();
                return 0;
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static int add(final Player player) {
        try (SqlSession sqlSession = Data.getSqlSession()){
            PlayerMapper playerMapper = sqlSession.getMapper(PlayerMapper.class);
            String password = Util.hash(player.getPassword());
            player.setPassword(password);
            playerMapper.insert(player);
            sqlSession.commit();
            return 0;
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static boolean isExistByName(final String name) {
        try (SqlSession sqlSession = Data.getSqlSession()) {
            PlayerMapper playerMapper = sqlSession.getMapper(PlayerMapper.class);
            return playerMapper.isExistByName(name) == 1;
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return true;
    }
}