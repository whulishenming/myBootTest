package com.lsm.boot.jpa.repository;

import com.lsm.boot.jpa.model.User;
import com.lsm.boot.jpa.model.UserStatisticsInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User> {

    List<User> findByNameAndIsDeleted(String name, byte isDeleted);

    User findFirstByName(String name);

    //like ?1
    List<User> findByPhoneNumLike(String phoneNum);

    //like ?1 (parameter bound with appended %)
    List<User> findByPhoneNumStartingWith(String phoneNum);

    //like ?1 (parameter bound wrapped in %)
    List<User> findByPhoneNumContaining(String phoneNum);

    Page<User> findByName(String phoneNum, Pageable pageable);

    @Query("select u from User u where u.createBy = ?1 and u.createTime < ?2")
    List<User> findByQuery(String createBy, Date createTime);

    @Query("select u from User u where u.createBy = :createBy and u.createTime < :createTime")
    List<User> findByQuery2(@Param("createBy") String createBy, @Param("createTime") Date createTime);

    @Modifying
    @Transactional
    @Query("update User u set u.name = :name, u.updateTime = :updateTime, u.updateBy = :updateBy where u.id = :id")
    int updateById(@Param("id") Long id, @Param("name") String name, @Param("updateTime") Date updateTime, @Param("updateBy") String updateBy);

    /**
     * 更新必须要加 @Modifying @Transactional
     * @param ids
     * @param name
     * @param updateTime
     * @param updateBy
     * @return
     */
    @Modifying
    @Transactional
    @Query("update User u set u.name = :name, u.updateTime = :updateTime, u.updateBy = :updateBy where u.id in :ids")
    int updateByIds(@Param("ids") List<Long> ids, @Param("name") String name, @Param("updateTime") Date updateTime, @Param("updateBy") String updateBy);

    /**
     * 创建一个结果集的接口来接收连表查询后的结果
     * @return
     */
    @Query("select count(u.id) as totalNum, u.createBy as createBy from User u group by u.createBy")
    List<UserStatisticsInfo> getStatisticsInfos();

    /**
     * 默认返回一个 List<Map<String, String>>
     * @return
     */
    @Query("select count(u.id) as totalNum, u.createBy as createBy from User u group by u.createBy")
    List<Map<String, String>> getStatisticsInfoMap();
}
