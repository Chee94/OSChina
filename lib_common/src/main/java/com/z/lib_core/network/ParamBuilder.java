package com.z.lib_core.network;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Creator:  chee
 * Date：2020/11/23 - 14:43
 * Desc： 请求参数封装类
 */
public class ParamBuilder {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Map<String, Object> map;

        public Builder() {
            this.map = new HashMap<>();
        }

        public Builder put(Map tmpMap) {
            map.putAll(tmpMap);
            return this;
        }

        public Builder put(String key, Object val) {
            map.put(key, val);
            return this;
        }

        public Map<String, Object> toMap() {
            return map;
        }

        public HashMap<String, Object> toHashMap() {
            return (HashMap<String, Object>) map;
        }

        public String toJson() {
            return JSON.toJSONString(map);
        }
    }

}
