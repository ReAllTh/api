package link.reallth.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import link.reallth.api.model.dto.interfaceinfo.InterfaceInfoAddDTO;
import link.reallth.api.model.po.InterfaceInfo;
import link.reallth.api.model.vo.InterfaceInfoVO;

/**
 * InterfaceInfoService
 *
 * @author ReAllTh
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    InterfaceInfoVO add(InterfaceInfoAddDTO interfaceInfoAddDTO);
}
