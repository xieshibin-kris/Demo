/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.miku.advs.core.common.constant.factory;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miku.advs.core.common.constant.DatasourceEnum;
import com.miku.advs.core.common.constant.cache.Cache;
import com.miku.advs.core.common.constant.cache.CacheKey;
import com.miku.advs.core.common.constant.state.ManagerStatus;
import com.miku.advs.core.common.constant.state.MenuStatus;
import com.miku.advs.core.log.LogObjectHolder;
import com.miku.advs.core.util.DateUtils;
import com.miku.advs.core.util.TableNameUtils;
import com.miku.advs.modular.system.entity.advertise.Advertise;
import com.miku.advs.modular.system.entity.advertise.AdvertiserHost;
import com.miku.advs.modular.system.entity.channel.Channel;
import com.miku.advs.modular.system.entity.channel.ChannelSub;
import com.miku.advs.modular.system.entity.channel.Package;
import com.miku.advs.modular.system.entity.channel.UserInfo;
import com.miku.advs.modular.system.entity.system.*;
import com.miku.advs.modular.system.mapper.advertise.AdvertiserHostMapper;
import com.miku.advs.modular.system.mapper.channel.*;
import com.miku.advs.modular.system.mapper.system.*;
import com.miku.advs.modular.system.service.advertise.AdvertiseService;
import com.miku.advs.modular.system.warpper.ChannelDeviceWrapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.miku.advs.core.common.constant.Const.VERSION_LIST;

/**
 * 常量的生产工厂
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:55:21
 */
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {

    private RoleMapper roleMapper = SpringContextHolder.getBean(RoleMapper.class);
    private DeptMapper deptMapper = SpringContextHolder.getBean(DeptMapper.class);
    private DictMapper dictMapper = SpringContextHolder.getBean(DictMapper.class);
    private UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
    private MenuMapper menuMapper = SpringContextHolder.getBean(MenuMapper.class);
    private NoticeMapper noticeMapper = SpringContextHolder.getBean(NoticeMapper.class);
    private AdvertiserHostMapper hostMapper = SpringContextHolder.getBean(AdvertiserHostMapper.class);
    private ChannelMapper channelMapper = SpringContextHolder.getBean(ChannelMapper.class);
    private DomainMapper domainMapper = SpringContextHolder.getBean(DomainMapper.class);
    private UserInfoMapper userInfoMapper = SpringContextHolder.getBean(UserInfoMapper.class);
    private AdvertiseService advertiseService = SpringContextHolder.getBean(AdvertiseService.class);
    private ActiveUserRecordMapper activeUserRecordMapper = SpringContextHolder.getBean(ActiveUserRecordMapper.class);
    private ChannelSubMapper channelSubMapper = SpringContextHolder.getBean(ChannelSubMapper.class);
    private PackageMapper packageMapper = SpringContextHolder.getBean(PackageMapper.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    @Override
    public String getUserNameById(Long userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            return user.getName();
        } else {
            return "--";
        }
    }

    @Override
    public String getUserAccountById(Long userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            return user.getAccount();
        } else {
            return "--";
        }
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.ROLES_NAME + "'+#roleIds")
    public String getRoleName(String roleIds) {
        if (ToolUtil.isEmpty(roleIds)) {
            return "";
        }
        Long[] roles = Convert.toLongArray(roleIds);
        StringBuilder sb = new StringBuilder();
        for (Long role : roles) {
            Role roleObj = roleMapper.selectById(role);
            if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
                sb.append(roleObj.getName()).append(",");
            }
        }
        return StrUtil.removeSuffix(sb.toString(), ",");
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleId")
    public String getSingleRoleName(Long roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = roleMapper.selectById(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "";
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_TIP + "'+#roleId")
    public String getSingleRoleTip(Long roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = roleMapper.selectById(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getDescription();
        }
        return "";
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.DEPT_NAME + "'+#deptId")
    public String getDeptName(Long deptId) {
        if (deptId == null) {
            return "";
        } else if (deptId == 0L) {
            return "顶级";
        } else {
            Dept dept = deptMapper.selectById(deptId);
            if (ToolUtil.isNotEmpty(dept) && ToolUtil.isNotEmpty(dept.getFullName())) {
                return dept.getFullName();
            }
            return "";
        }
    }

    @Override
    public String getMenuNames(String menuIds) {
        Long[] menus = Convert.toLongArray(menuIds);
        StringBuilder sb = new StringBuilder();
        for (Long menu : menus) {
            Menu menuObj = menuMapper.selectById(menu);
            if (ToolUtil.isNotEmpty(menuObj) && ToolUtil.isNotEmpty(menuObj.getName())) {
                sb.append(menuObj.getName()).append(",");
            }
        }
        return StrUtil.removeSuffix(sb.toString(), ",");
    }

    @Override
    public String getMenuName(Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            return "";
        } else {
            Menu menu = menuMapper.selectById(menuId);
            if (menu == null) {
                return "";
            } else {
                return menu.getName();
            }
        }
    }

    @Override
    public Menu getMenuByCode(String code) {
        if (ToolUtil.isEmpty(code)) {
            return new Menu();
        } else if (code.equals("0")) {
            return new Menu();
        } else {
            Menu param = new Menu();
            param.setCode(code);
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>(param);
            Menu menu = menuMapper.selectOne(queryWrapper);
            if (menu == null) {
                return new Menu();
            } else {
                return menu;
            }
        }
    }

    @Override
    public String getMenuNameByCode(String code) {
        if (ToolUtil.isEmpty(code)) {
            return "";
        } else if (code.equals("0")) {
            return "顶级";
        } else {
            Menu param = new Menu();
            param.setCode(code);
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>(param);
            Menu menu = menuMapper.selectOne(queryWrapper);
            if (menu == null) {
                return "";
            } else {
                return menu.getName();
            }
        }
    }

    @Override
    public Long getMenuIdByCode(String code) {
        if (ToolUtil.isEmpty(code)) {
            return 0L;
        } else if (code.equals("0")) {
            return 0L;
        } else {
            Menu menu = new Menu();
            menu.setCode(code);
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>(menu);
            Menu tempMenu = this.menuMapper.selectOne(queryWrapper);
            return tempMenu.getMenuId();
        }
    }

    @Override
    public String getDictName(Long dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        } else {
            Dict dict = dictMapper.selectById(dictId);
            if (dict == null) {
                return "";
            } else {
                return dict.getName();
            }
        }
    }

    @Override
    public String getNoticeTitle(Long dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        } else {
            Notice notice = noticeMapper.selectById(dictId);
            if (notice == null) {
                return "";
            } else {
                return notice.getTitle();
            }
        }
    }

    @Override
    public String getDictsByName(String name, String code) {
        Dict temp = new Dict();
        temp.setName(name);
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>(temp);
        Dict dict = dictMapper.selectOne(queryWrapper);
        if (dict == null) {
            return "";
        } else {
            QueryWrapper<Dict> wrapper = new QueryWrapper<>();
            wrapper = wrapper.eq("PID", dict.getDictId());
            List<Dict> dicts = dictMapper.selectList(wrapper);
            for (Dict item : dicts) {
                if (item.getCode() != null && item.getCode().equals(code)) {
                    return item.getName();
                }
            }
            return "";
        }
    }

    @Override
    public String getSexName(String sexCode) {
        return getDictsByName("性别", sexCode);
    }

    @Override
    public String getStatusName(String status) {
        return ManagerStatus.getDescription(status);
    }

    @Override
    public String getMenuStatusName(String status) {
        return MenuStatus.getDescription(status);
    }

    @Override
    public List<Dict> findInDict(Long id) {
        if (ToolUtil.isEmpty(id)) {
            return null;
        } else {
            QueryWrapper<Dict> wrapper = new QueryWrapper<>();
            List<Dict> dicts = dictMapper.selectList(wrapper.eq("PID", id));
            if (dicts == null || dicts.size() == 0) {
                return null;
            } else {
                return dicts;
            }
        }
    }

    @Override
    public String getCacheObject(String para) {
        return LogObjectHolder.me().get().toString();
    }

    @Override
    public List<Long> getSubDeptId(Long deptId) {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper = wrapper.like("PIDS", "%[" + deptId + "]%");
        List<Dept> depts = this.deptMapper.selectList(wrapper);

        ArrayList<Long> deptids = new ArrayList<>();

        if (depts != null && depts.size() > 0) {
            for (Dept dept : depts) {
                deptids.add(dept.getDeptId());
            }
        }

        return deptids;
    }

    @Override
    public List<Long> getParentDeptIds(Long deptId) {
        Dept dept = deptMapper.selectById(deptId);
        String pids = dept.getPids();
        String[] split = pids.split(",");
        ArrayList<Long> parentDeptIds = new ArrayList<>();
        for (String s : split) {
            parentDeptIds.add(Long.valueOf(StrUtil.removeSuffix(StrUtil.removePrefix(s, "["), "]")));
        }
        return parentDeptIds;
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.ADVERTISER_HOSE_NAME + "'+#advertiserId")
    public String getHostName(Integer advertiserId) {
        AdvertiserHost advertiser = hostMapper.selectByPrimaryKey(advertiserId);
        if(advertiser !=null){
            return advertiser.getName();
        } else {
            return "";
        }

    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.CHANNEL_NAME + "'+#channelId")
    public String getChannelName(Integer channelId) {
        Channel channel = channelMapper.selectById(channelId);
        if(channel!=null){
            return channel.getName();
        } else {
            return "";
        }
    }

    @Override
    @Cacheable(value = Cache.CONSTANT,key = "'"+CacheKey.DOMAIN_ACTIVE_NAME+"'")
    public Object getDomainName() {
        return getDomainsName();
    }

    @Override
    public Object getDomainsName() {
        Page page = new Page();
        Page<Domain> list = domainMapper.selectDomainList(page,1);
        StringBuffer name = new StringBuffer();
        for (Domain domain:list.getRecords()){
            name.append(domain.getUrl()+",");
        }
        String domain = name.toString();
        if(!ToolUtil.isEmpty(name.toString())){
            domain = name.substring(0,name.lastIndexOf(","));
        }
        return domain;
    }

    @Override
    public ChannelSub getChannelSubById(Integer cid) {
        return channelSubMapper.selectById(cid);
    }

    @Override
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @Cacheable(value = Cache.CONSTANT,key = "'"+CacheKey.PACKAGE_NAME+"'+#packageId")
    public String getPackageName(Integer packageId) {
        Package pac  = packageMapper.selectById(packageId);
        if(pac!=null){
            return pac.getPakage();
        }
        return "";
    }

    @Override
    public Map<Integer, String> getSystemVersionList() {
        Map<Integer,String> map = new HashMap<>();
        for (Integer key:VERSION_LIST){
            map.put(key, ChannelDeviceWrapper.getSystemVersion(key));
        }
        return map;
    }


    @Override
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @Cacheable(value = Cache.CONSTANT,key = "'"+CacheKey.PARTNER_NAME+"'+#partnerId")
    public String getPartnerNameById(Integer partnerId) {
        if(!ToolUtil.isEmpty(partnerId)){
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(partnerId.intValue());
            if(userInfo != null){
                return userInfo.getUsername();
            }
        }
        return "";
    }

    @Override
    public Advertise getAdvertiseById(String advertiseId) {
        return advertiseService.getAdvertiseById(advertiseId);
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.CHANNEL_BEAN + "'+#channelId")
    public Channel getChannelById(Integer channelId) {
        return  channelMapper.selectById(channelId);
    }

    @Override
    public Integer getActiveUserCount(Integer channelId, String date) {
        Long startTime = 0L;
        Long endTime = 0L;
        if(!ToolUtil.isEmpty(date)){
            System.out.println("date:"+date);
            startTime = DateUtils.getDateFromStr(date+" 00:00:00").getTime();
            endTime = DateUtils.getDateFromStr(date +" 23:59:59").getTime();
        }
        String tableName = TableNameUtils.getDateSuffix(DateUtils.getDayFromStr(date));
        Map<String,Object> map = activeUserRecordMapper.getActiveUserCount(startTime,endTime,channelId,tableName);
        if(map != null){
            return ((Long) map.get("count")).intValue();
        }
        return 0 ;
    }

}
