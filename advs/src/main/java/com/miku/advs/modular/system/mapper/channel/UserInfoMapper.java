package com.miku.advs.modular.system.mapper.channel;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miku.advs.modular.system.entity.channel.UserInfo;
import java.util.List;

public interface UserInfoMapper extends BaseMapper<UserInfo> {
    int deleteByPrimaryKey(Integer userid);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userid);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);
}