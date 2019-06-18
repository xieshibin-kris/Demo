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
package com.miku.advs.core.common.constant.cache;

/**
 * 缓存标识前缀集合,常用在ConstantFactory类中
 *
 * @author fengshuonan
 * @date 2017-04-25 9:37
 */
public interface CacheKey {

    /**
     * 角色名称(多个)
     */
    String ROLES_NAME = "roles_name_";

    /**
     * 角色名称(单个)
     */
    String SINGLE_ROLE_NAME = "single_role_name_";

    /**
     * 角色英文名称
     */
    String SINGLE_ROLE_TIP = "single_role_tip_";

    /**
     * 部门名称
     */
    String DEPT_NAME = "dept_name_";

    /**
     * 渠道名称
     */
    String CHANNEL_NAME = "channel_name_";

    /**
     * 广告主名称
     */
    String ADVERTISER_HOSE_NAME = "advertiser_host_name_";

    /**
     * advertise对象
     */
    String ADVERTISE_BEAN = "advertise_bean_";

    /**
     * 可用域名名称
     */
    String DOMAIN_ACTIVE_NAME = "domain_active_name_";

    /**
     * channel对象
     */
    String CHANNEL_BEAN = "channel_bean_";

    /**
     * 渠道商名称
     */
    String PARTNER_NAME = "partner_name_";

    /**
     * 配置缓存
     */
    String SETMAPKEY = "set_map_key";

    String PACKAGE_NAME = "package_name_";

    /**
     * 国家code
     */
    String COUNTRY_CODE = "country_code_";
    /**
     * 国家集合
     */
    String COUNTRY_LIST = "country_list";
}
