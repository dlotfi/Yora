package com.example.yora.services;

import com.example.yora.infrastructure.YoraApplication;

public class LiveMessageService extends BaseLiveService {
    protected LiveMessageService(YoraApplication application, YoraWebService api) {
        super(application, api);
    }
}
