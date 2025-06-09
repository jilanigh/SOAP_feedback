package com.feedback.config;

import com.feedback.service.FeedbackService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.SoapEnvelopeLoggingInterceptor;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.server.endpoint.adapter.method.MarshallingPayloadMethodProcessor;
import org.springframework.ws.server.endpoint.adapter.DefaultMethodEndpointAdapter;
import org.springframework.ws.server.endpoint.mapping.PayloadRootAnnotationMethodEndpointMapping;
import java.util.List;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("com.feedback.model");
        return marshaller;
    }

    @Bean
    public DefaultMethodEndpointAdapter defaultMethodEndpointAdapter() {
        DefaultMethodEndpointAdapter adapter = new DefaultMethodEndpointAdapter();
        MarshallingPayloadMethodProcessor processor = new MarshallingPayloadMethodProcessor(marshaller());
        adapter.setMethodArgumentResolvers(List.of(processor));
        adapter.setMethodReturnValueHandlers(List.of(processor));
        return adapter;
    }

    @Bean
    public PayloadRootAnnotationMethodEndpointMapping endpointMapping() {
        PayloadRootAnnotationMethodEndpointMapping mapping = new PayloadRootAnnotationMethodEndpointMapping();
        mapping.setInterceptors(new EndpointInterceptor[] {
            new PayloadValidatingInterceptor(),
            new SoapEnvelopeLoggingInterceptor()
        });
        return mapping;
    }

    @Bean(name = "feedback")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema feedbackSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("FeedbackPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://feedback.com/service");
        wsdl11Definition.setSchema(feedbackSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema feedbackSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/feedback.xsd"));
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        PayloadValidatingInterceptor validatingInterceptor = new PayloadValidatingInterceptor();
        validatingInterceptor.setValidateRequest(true);
        validatingInterceptor.setValidateResponse(true);
        validatingInterceptor.setXsdSchema(feedbackSchema());
        interceptors.add(validatingInterceptor);
        
        interceptors.add(new SoapEnvelopeLoggingInterceptor());
    }
} 