package com.example.demo.common.validation.group;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * @author linjian
 * @date 2021/3/23
 */
@GroupSequence({Default.class, VerifySequence.VerifyEnum.class})
public interface VerifySequence {

    interface VerifyEnum {
    }
}
