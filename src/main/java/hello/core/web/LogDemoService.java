package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    //private final ObjectProvider<MyLogger> myLoggerProvider;
    private final Provider<MyLogger> myLoggerProvider;

    public void logic(String id) {
        //MyLogger myLogger = myLoggerProvider.getObject();
        MyLogger myLogger = myLoggerProvider.get();
        myLogger.log("service id = " + id);
    }
}
