
# Monolith To Microservices

Twitter @spencerbgibb

https://github.com/spencergibb/monolith-to-microservices

This repository will take a monolithic application and decompose it to microservices using Spring Cloud Gateway

### Tag:  stage1

Hello/Fortune monolith
- Hello service
- Fortune service (external API $$$)
- UI

### Tag:  stage2

Stand up gateway, point at old monolith

### Tag:  stage3

Move UI to separate app (path rewrite)

### Tag:  stage4

Add service discovery

### Tag:  stage5

Move hello service (with filter to transform req param to path) (with metadata expression to include, and rewrite expression in metadata)

### Tag:  stage6

Add circuit breaker to old fortune service with fallback

### Tag:  stage7

Move fortune service as v2

### Tag:  stage8

Add rate limiting to fortune v2

Retire v1 (after date filter?)
