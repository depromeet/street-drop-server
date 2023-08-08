import React from 'react';
import clsx from 'clsx';
import styles from './styles.module.css';

interface Callout {
    title: string;
    text: string;
    link: string;
}


const callouts: Callout[] = [
    {
        title: "비즈니스",
        text: "유저 행동 로깅과 어드민 페이지를 통해, 사용자의 행동을 분석하고, 통계를 내며 서비스를 개선해나가고 있습니다.",
        link: "/street-drop-server/docs/intro",
    },
    {
        title: "모니터링",
        text: "프로메테우스 및 그라파나를 통해 서비스의 상태 모니터링과 500대 에러 Slack 알림, 슬로우 쿼리 모니터링 등을 통해서, 서비스의 안정성을 높이고 있습니다.",
        link: "/street-drop-server/docs/intro",
    },
    {
        title: "설계",
        text: "API 서버, Admin 서버, Batch 서버, 알림 서버 등을 분리하여, 서비스의 안정성과 확장성을 높이고 있습니다.",
        link: "/street-drop-server/docs/intro",
    },
    {
        title: "문서화",
        text: "Swagger를 통해 API 문서를 자동화하고 있습니다.",
        link: "/street-drop-server/docs/intro",
    },
    {
        title: "로그",
        text: "ELK를 통해 서비스의 로그를 수집하고 있습니다.",
        link: "/street-drop-server/docs/intro",
    },
    {
        title: "성능",
        text: "JMeter를 통해 서비스의 성능을 측정하고 있습니다.",
        link: "/street-drop-server/docs/intro",
    },
];

function Introduction(): JSX.Element {
    return (
        <>
            <div className={clsx("row margin-bottom--sm", styles.hero)}>
                <div className="col col--8">
                    <div>
                        <h2 className={clsx("margin-bottom--lg", styles.hero__title)}>STREET DROP ENGINEERING</h2>
                        <p className={clsx("margin-bottom--md", styles.hero__text)}>
                            스트릿 드랍을 만들어가는 백엔드 개발자들의 다양한 경험과 실제 사례를 바탕으로한 문제 해결과정을 공유합니다.
                            높은 수준의 코드 품질과 안정적인 서비스를 만들기 위해 노력하고 있습니다.
                        </p>
                        <p className={clsx("margin-bottom--sm", styles.hero__text)}>
                            비즈니스, 모니터링, 설계, 문서화, 로그, 성능 등 다양한 주제를 다루고 있습니다.
                        </p>
                    </div>
                </div>
            </div>
        </>
    );
}


function Callout(props: Callout): JSX.Element {
    const handleCalloutClick = () => {
        window.location.href = props.link;
    };

    return (
        <div className={clsx("margin-top--xl shadow--lg", styles.callout)} onClick={handleCalloutClick}>
            <div className="card__header">
                <h3 className={styles.callout__title}>
                    {props.title}
                </h3>
            </div>
            <div className="card__body">
                <p>
                    <a href={props.link} className={styles.callout__link}>
                        <span className="link--inset" aria-hidden="true"></span>
                        {props.text}
                    </a>
                </p>
            </div>
            <div className={styles.callout__bottom}/>
        </div>
    );
}


export default function AboutStreetDrop(): JSX.Element {
    return (
        <section className="padding-vert--xl container">
            <Introduction/>
            <div className="row">
                {callouts.map((c) => (
                    <div key={c.title} className="col col--4">
                        <Callout title={c.title} text={c.text} link={c.link}/>
                    </div>
                ))}
            </div>
        </section>
    );
}
