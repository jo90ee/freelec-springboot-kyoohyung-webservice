#!/user/bin/env bash
# 쉬고 있는 profile 찾기: real1이 사용중이면 rea2가 쉬고 있고, 반대면 real1이 쉬고 있음

function find_idle_profile()
{
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}"http://localhost/profile) # 현재 nginx가 바라보고 있는 스프링 부트가 정상적으로 수행 중인지 확인한다. 응답값을 HttpsStatus로 받는다.

    if [ ${RESPONSE_CODE} -ge 400 ] #400 보다 크면(즉, 40x/50x에러모두 포함)
    then
        CURRENT_PROFILE=rea2
    else
        CURRENT_PROFILE=$(curl -s http://localhost/profile)
    fi

    if [ ${CURRENT_PROFILE} == real1 ]
    then
        IDLE_PROFILE=real2  # nginx와 연결되지 안은 profile, 스프링부트 프로젝트를 이  profile로 연결하기 위해 반환 한다.
    else
        IDLE_PROFILE=real1
    fi

    echo "${IDLE_PROFILE}"
}

#쉬고 있는 profile의 포트 찾기
function find_idle_port()
{
    IDLE_PROFILE=$( find_idle_profile )

    if [ ${IDLE_PROFILE} == real1 ]
    then
        echo "8081"
    else
        echo "8082"
    fi
}