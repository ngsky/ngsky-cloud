package com.ngsky.quartzngsky.code;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <dl>
 * <dt>JobEntity</dt>
 * <dd>Description: JOB 实体</dd>
 * <dd>CreateDate: 12/12/2018 11:52 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
@Entity
@Table(name = "JOB_ENTITY")
public class JobEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;           //job编号
    @Column(name = "name")
    private String name;          //job名称
    @Column(name = "group")
    private String group;         //job组名
    @Column(name = "cron")
    private String cron;          //执行的cron
    @Column(name = "status")
    private String status;        //job的执行状态,start/pause/stop...
    @Column(name = "is_concurrent")
    private Integer isConcurrent; //job是否与并发方法执行
    @Column(name = "description")
    private String description;   //job描述信息
    @Column(name = "ctime")
    private String ctime;         //job创建时间
    @Column(name = "utime")
    private String utime;         //job更新时间
    @Column(name = "parameter")
    private String parameter;     //job的参数

    public JobEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsConcurrent() {
        return isConcurrent;
    }

    public void setIsConcurrent(Integer isConcurrent) {
        this.isConcurrent = isConcurrent;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    //新增Builder模式,可选,选择设置任意属性初始化对象
    public JobEntity(Builder builder) {
        id = builder.id;
        name = builder.name;
        group = builder.group;
        cron = builder.cron;
        parameter = builder.parameter;
        description = builder.description;
        status = builder.status;
        isConcurrent = builder.isConcurrent;
        ctime = builder.ctime;
        utime = builder.utime;
    }

    public static class Builder {
        private Long id;           //job编号
        private String name;          //job名称
        private String group;         //job组名
        private String cron;          //执行的cron
        private String status;        //job的执行状态,start/pause/stop...
        private Integer isConcurrent; //job是否与并发方法执行
        private String description;   //job描述信息
        private String ctime;         //job创建时间
        private String utime;         //job更新时间
        private String parameter;     //job的参数

        public Builder withId(Long i) {
            id = i;
            return this;
        }

        public Builder withName(String n) {
            name = n;
            return this;
        }

        public Builder withGroup(String g) {
            group = g;
            return this;
        }

        public Builder withCron(String c) {
            cron = c;
            return this;
        }

        public Builder withParameter(String p) {
            parameter = p;
            return this;
        }

        public Builder withDescription(String d) {
            description = d;
            return this;
        }

        public Builder withIsConcurrent(Integer i) {
            isConcurrent = i;
            return this;
        }

        public Builder withStatus(String s) {
            status = s;
            return this;
        }

        public Builder withCtime(String c) {
            ctime = c;
            return this;
        }

        public Builder withUtime(String u) {
            utime = u;
            return this;
        }

        public JobEntity newJobEntity() {
            return new JobEntity(this);
        }
    }
}
