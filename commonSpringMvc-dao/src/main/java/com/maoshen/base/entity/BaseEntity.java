/**   
 * @Description:(用一句话描述该类做什么)
 * @author Daxian.jiang
 * @Email  Daxian.jiang@vipshop.com
 * @Date 2015年7月28日 下午4:54:53
 * @Version V1.0   
 */
package com.maoshen.base.entity;

public class BaseEntity {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BaseEntity(long id) {
        super();
        this.id = id;
    }

    public BaseEntity() {

    }
}