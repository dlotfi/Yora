package com.example.yora.services;

import com.example.yora.infrastructure.YoraApplication;

public class LiveAccountService extends BaseLiveService {
    protected LiveAccountService(YoraApplication application, YoraWebService api) {
        super(application, api);
    }
}
