package com.zc.utils;

import java.util.List;

public class MyResponse {
    private String code;
    private MyData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MyData getData() {
        return data;
    }

    public void setData(MyData data) {
        this.data = data;
    }

    public class MyData {
        private int allCount;
        private List<Problem> list;

        public int getAllCount() {
            return allCount;
        }

        public void setAllCount(int allCount) {
            this.allCount = allCount;
        }

        public List<Problem> getList() {
            return list;
        }

        public void setList(List<Problem> list) {
            this.list = list;
        }

        public class Problem {
            private String problemId;

            public String getProblemId() {
                return problemId;
            }

            public void setProblemId(String problemId) {
                this.problemId = problemId;
            }
        }
    }
}
