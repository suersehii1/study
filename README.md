1. JWT_EXAMPLE : Jason web token 예제
2. PORTAL_EXMAPLE/vue : 메뉴가 상단에 있는 3level portal 예
3. msaDiscovery : netflix 의 Eureka Server (Discover Server)
4. userSevice: netflix 의 Eureka client

5. apigateway-service: spring cloud 의 Reactive gateway 역확을 함.
    msaDiscovery에 gateway 로 등록되고, 
    요청이 들어오면 무슨 요청인지 파악하고 discovery server 에서 요청에 맞는 msa 를 찾아 route 한다.
6. firstService: msaDiscovery 에 등록되는 MSA
7. secondService: msaDiscovery 에 등록되는 MSA
8. 
   
