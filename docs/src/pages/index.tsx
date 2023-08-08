import React from 'react';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import Layout from '@theme/Layout';
import HomepageFeatures from '@site/src/components/HomepageFeatures';

import styles from './index.module.css';

function HomepageHeader() {
  return (
      <header className={styles.bannerContainer}>
          <div className={styles.titleContainer}>
              <div className={styles.logo}/>
              <p className={styles.smallTitle}>Street Drop Engineering</p>
              <h2 className={styles.mainTitle}>스트릿 드랍 개발간</h2>
              <h2 className={styles.mainTitle}>다양한 경험을 공유합니다.</h2>
          </div>
      </header>
  );
}

export default function Home(): JSX.Element {
  const {siteConfig} = useDocusaurusContext();
  return (
      <Layout>
          <HomepageHeader/>
          <main>
              <HomepageFeatures/>
          </main>
      </Layout>
  );
}
