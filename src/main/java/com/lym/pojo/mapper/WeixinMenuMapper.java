package com.lym.pojo.mapper;

import com.lym.pojo.WeixinMenu;
import com.lym.pojo.WeixinMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeixinMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weixin_menu
     *
     * @mbggenerated
     */
    int countByExample(WeixinMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weixin_menu
     *
     * @mbggenerated
     */
    int deleteByExample(WeixinMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weixin_menu
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weixin_menu
     *
     * @mbggenerated
     */
    int insert(WeixinMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weixin_menu
     *
     * @mbggenerated
     */
    int insertSelective(WeixinMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weixin_menu
     *
     * @mbggenerated
     */
    List<WeixinMenu> selectByExample(WeixinMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weixin_menu
     *
     * @mbggenerated
     */
    WeixinMenu selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weixin_menu
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") WeixinMenu record, @Param("example") WeixinMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weixin_menu
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") WeixinMenu record, @Param("example") WeixinMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weixin_menu
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(WeixinMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_weixin_menu
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(WeixinMenu record);
}