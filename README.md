# Monitoring & Alerting System
## 프로젝트 개요
***
서비스의 안정성을 모니터링하고 장애를 빠르게 감지하기 위한 모니터링 및 Alert 시스템을 구축하는 것이 목표

Prometheus를 이용하여 애플리케이션의 매트릭을 수집하고, Grafana를 통해 주요 서비스 지표를 시각화

SLO 기준을 벗어날 경우 Alert가 발생하도록 Prometheus Alert Rule 설정

## 시스템 아키텍처
***
> Client  
> 
> → Application Server (SpringBoot)
> 
> → Prometheus (Metrics 수집)
> 
> → Grafana (DashBoard 시각화) 
> 
> → Alert Rule (장애 감지)

* Application Server 
  * 서비스 요청을 처리하며 Prometheus metric을 제공
* Prometheus
  * 애플리케이션 메트릭을 주기적으로 수집하고 Alert Rule을 통해 장애를 감지
* Grafana
  * Prometheus 데이터를 기반으로 서비스 상태를 시각적으로 확인할 수 있는 Dashboard를 제공

## SLI & SLO
***
### SLI
1. Error Rate
```
(sum(rate(http_server_requests_seconds_count{job=“api”, outcome=“SERVER_ERROR”}[5m])) or vector(0))
/clamp_min(sum(rate(http_server_requests_seconds_count{job=“api”}[5m])), 1)* 100
```      
2. p95 Latency
```
histogram_quantile(0.95, sum(rate(http_server_requests_seconds_bucket{job=“api”}[5m])) by (le))
```
3. Availability
```
( 1 - ( sum(rate(http_server_requests_seconds_count{job=“api”, outcome=“SERVER_ERROR”}[5m]))
or vector(0) ) / clamp_min(sum(rate(http_server_requests_seconds_count{job=“api”}[5m])), 1)) * 100
```
5. Request Rate
```
sum(rate(http_server_requests_seconds_count{job=“api”}[5m]))
sum(increase(http_server_requests_seconds_count{job=“api”}[5m]))
```
### SLO
1. Error Rate < 1%
2. p95 Latency < 300ms
3. Availabillity >=99%!

## 대시보드 & Prometheus Alert
***
![img.png](img.png)

![스크린샷 2026-03-13 오전 5.08.51.png](..%2F..%2F..%2F..%2FDesktop%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202026-03-13%20%EC%98%A4%EC%A0%84%205.08.51.png)

![스크린샷 2026-03-13 오후 9.24.49.png](..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2F5v%2Fmh8l76rn2qvbx47jk822w1bw0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_kGlFQN%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202026-03-13%20%EC%98%A4%ED%9B%84%209.24.49.png)

![스크린샷 2026-03-13 오후 9.25.56.png](..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2F5v%2Fmh8l76rn2qvbx47jk822w1bw0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_a1Jhh3%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202026-03-13%20%EC%98%A4%ED%9B%84%209.25.56.png)

![스크린샷 2026-03-13 오후 9.26.09.png](..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2F5v%2Fmh8l76rn2qvbx47jk822w1bw0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_klJtHh%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202026-03-13%20%EC%98%A4%ED%9B%84%209.26.09.png)