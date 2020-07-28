package com.yang.workbench.service;

import com.yang.vo.PaginationVO;
import com.yang.workbench.domain.Activity;

import java.util.Map;

public interface ActivityService {
    boolean save(Activity activity);

    PaginationVO<Activity> pageList(Map<String, Object> map);
}
