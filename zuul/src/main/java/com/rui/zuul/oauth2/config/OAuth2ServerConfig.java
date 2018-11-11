package com.rui.zuul.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * @author : Rui
 * @Date : 2018/11/7
 * @Time : 9:58
 **/
@Configuration
public class OAuth2ServerConfig {

    @Configuration
    @EnableResourceServer()
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.stateless(true);
            // 如果关闭 stateless，则 accessToken 使用时的 session id 会被记录，后续请求不携带 accessToken 也可以正常响应
//            resources.resourceId(QQ_RESOURCE_ID).stateless(false);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .requestMatchers()
                    // 保险起见，防止被主过滤器链路拦截
                    .antMatchers("/**")
                    .and()
                    .authorizeRequests()
                    .antMatchers("/","/index","login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/app1/**","/admin/**")
                    .access("#oauth2.hasScope('write')");
            // @formatter:on
        }

    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Resource
        private AuthenticationManager authenticationManager;
        @Resource
        private UserDetailsService userDetailsService;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

            // @formatter:off
            clients.inMemory().withClient("test")
                    .authorizedGrantTypes("password", "refresh_token")
                    .scopes("write")
                    .authorities("ROLE_ADMIN")
                    .secret("secret");
            // @formatter:on
        }

        @Bean
        public ApprovalStore approvalStore() {
            TokenApprovalStore store = new TokenApprovalStore();
            store.setTokenStore(tokenStore());
            return store;
        }

        @Bean
        public TokenStore tokenStore() {
            return new InMemoryTokenStore();
            // 需要使用 redis 的话，放开这里
//            return new RedisTokenStore(redisConnectionFactory);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .tokenStore(tokenStore())
                    .authenticationManager(authenticationManager)
                    .userDetailsService(userDetailsService)
                    // 2018-4-3 增加配置，允许 GET、POST 请求获取 token，即访问端点：oauth/token
                    .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

            endpoints.reuseRefreshTokens(true);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
            oauthServer.allowFormAuthenticationForClients();
        }

    }

}
