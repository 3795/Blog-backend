package com.seven.Blog.convert;

import com.seven.Blog.dto.NavigationDTO;
import com.seven.Blog.pojo.Navigation;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Seven.wk
 * Description: 将Navigation模型转化为NavigationDTO模型
 * Created At 2018/10/04
 */
public class NavigationToNavigationDTO {

    public static NavigationDTO convert(Navigation navigation) {
        NavigationDTO navigationDTO = new NavigationDTO();
        BeanUtils.copyProperties(navigation, navigationDTO);
        return navigationDTO;
    }

    public static List<NavigationDTO> convert(List<Navigation> navigationList) {
        return navigationList.stream()
                .map(NavigationToNavigationDTO::convert)
                .collect(Collectors.toList());
    }

}
