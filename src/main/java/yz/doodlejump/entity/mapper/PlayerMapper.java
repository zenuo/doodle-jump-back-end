package yz.doodlejump.entity.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import yz.doodlejump.entity.bean.Player;

/**
 * 玩家映射类
 */
public interface PlayerMapper {
    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.CHAR),
            @Result(property = "bio", column = "bio", javaType = String.class, jdbcType = JdbcType.CHAR),
            @Result(property = "email", column = "email", javaType = String.class, jdbcType = JdbcType.CHAR),
            @Result(property = "password", column = "password", javaType = String.class, jdbcType = JdbcType.CHAR),
            @Result(property = "record", column = "record", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "coin", column = "coin", javaType = Integer.class, jdbcType = JdbcType.INTEGER)
    })

    @Select("SELECT id, name, bio, email, record, coin FROM PLAYER WHERE id=#{id} LIMIT 1;")
    Player getPlayerById(@Param("id") final int id);

    @Select("SELECT id, name, bio, email, record, coin FROM PLAYER WHERE name=#{name} LIMIT 1;")
    Player getPlayerByName(@Param("name") final String name);

    @Select("SELECT IF(#{password}=(SELECT password FROM PLAYER WHERE name=#{name} LIMIT 1),0,1);")
    int checkNameAndPassword(@Param("name") final String name, @Param("password") final String password);

    @Select("SELECT COUNT(*) FROM PLAYER WHERE name=#{name} LIMIT 1;")
    int isExistByName(@Param("name") final String name);

    @Insert("INSERT INTO PLAYER(id, name, bio, email, password, record, coin) VALUES(#{id}, #{name}, #{bio}, #{email}, #{password}, #{record}, #{coin});")
    void insert(final Player player);

    @Update("UPDATE PLAYER SET record=#{record} WHERE id=#{id};")
    void updateRecord(@Param("id") final int id, @Param("record") final int record);

    @Update("UPDATE PLAYER SET coin=#{coin} WHERE id=#{id};")
    void updateCoin(@Param("id") final int id, @Param("coin") final int coin);
}
