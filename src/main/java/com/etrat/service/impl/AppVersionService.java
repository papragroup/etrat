package com.etrat.service.impl;

import com.etrat.domain.VersionEntity;
import com.etrat.repository.VersionRepository;
import com.etrat.service.dto.CheckVersionRequest;
import com.etrat.service.dto.VersionSupportDotResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AppVersionService {
    @Autowired
    private VersionRepository versionRepository;

    public VersionSupportDotResponse checkVersion(CheckVersionRequest checkVersionRequest) {
        VersionSupportDotResponse versionSupportDotResponse = new VersionSupportDotResponse();
        List<VersionEntity> byAppVersionAndOperationSystem = versionRepository.findByAppVersionAndOperationSystem(
            checkVersionRequest.getAppVersion(),
            checkVersionRequest.getOperationSystem()
        );
        if (!ObjectUtils.isEmpty(byAppVersionAndOperationSystem)) {
            VersionEntity versionEntity = byAppVersionAndOperationSystem.get(0);
            versionSupportDotResponse.setAppVersionSupported(false);
            versionSupportDotResponse.setMessage(versionEntity.getMessage());
            versionSupportDotResponse.setNextAppUrl(versionEntity.getNextAppVersion());
        }
        return versionSupportDotResponse;
    }
}
