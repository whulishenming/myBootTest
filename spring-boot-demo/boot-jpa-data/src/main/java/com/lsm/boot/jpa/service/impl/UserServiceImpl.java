package com.lsm.boot.jpa.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.lsm.boot.jpa.model.User;
import com.lsm.boot.jpa.model.UserStatisticsInfo;
import com.lsm.boot.jpa.param.UserParam;
import com.lsm.boot.jpa.repository.UserBatchRepository;
import com.lsm.boot.jpa.repository.UserRepository;
import com.lsm.boot.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBatchRepository userBatchRepository;

    @Override
    public void save(User user) {

        userRepository.save(user);
    }

    @Override
    public void batchInsert(List<User> list) {

        userBatchRepository.batchInsert(list);
    }

    @Override
    public List<User> findByName(String name) {

        return userRepository.findByNameAndIsDeleted(name, (byte) 0);
    }

    @Override
    public User findFirstByName(String name) {

        return userRepository.findFirstByName(name);
    }

    @Override
    public List<User> findByPhoneNumLike(String phoneNum) {

        return userRepository.findByPhoneNumLike(phoneNum);
    }

    @Override
    public List<User> findByPhoneNumStartingWith(String phoneNum) {

        return userRepository.findByPhoneNumStartingWith(phoneNum);
    }

    @Override
    public List<User> findByPhoneNumContaining(String phoneNum) {

        return userRepository.findByPhoneNumContaining(phoneNum);
    }

    @Override
    public Page<User> findByName(String phoneNum, Pageable pageable) {

        return userRepository.findByName(phoneNum, pageable);
    }

    @Override
    public List<User> findByQuery(String createBy, Date createTime) {

        return userRepository.findByQuery(createBy, createTime);
    }

    @Override
    public List<User> findByQuery2(String createBy, Date createTime) {

        return userRepository.findByQuery2(createBy, createTime);
    }

    @Override
    public int updateById(Long id, String name, String updateBy) {

        return userRepository.updateById(id, name, new Date(), updateBy);
    }

    @Override
    public int updateByIds(List<Long> ids, String name, String updateBy) {

        return userRepository.updateByIds(ids, name, new Date(), updateBy);
    }

    @Override
    public List<UserStatisticsInfo> getStatisticsInfos() {

        return userRepository.getStatisticsInfos();
    }

    @Override
    public List<Map<String, String>> getStatisticsInfoMap() {

        return userRepository.getStatisticsInfoMap();
    }

    @Override
    public Page<User> findByUserParam(UserParam param) {

        return userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();

                if (!StringUtils.isEmpty(param.getName())){
                    Predicate namePredicate = criteriaBuilder.equal(root.<String>get("name"), param.getName());

                    predicates.add(namePredicate);
                }
                if (!StringUtils.isEmpty(param.getPhoneNum())){
                    Predicate phoneNumPredicate = criteriaBuilder.like(root.get("phoneNum"), "%" + param.getPhoneNum() + "%");

                    predicates.add(phoneNumPredicate);
                }
                if (!"-1".equals(param.getCreateBy())){
                    Predicate createByPredicate = criteriaBuilder.equal(root.get("createBy"), param.getCreateBy());

                    predicates.add(createByPredicate);
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, new PageRequest(param.getPageNum() - 1, param.getSize(), new Sort(Sort.Direction.DESC, "updateBy")));
    }
}
