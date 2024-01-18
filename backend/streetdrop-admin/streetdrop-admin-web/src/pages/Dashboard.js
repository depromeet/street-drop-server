import React from "react";
import DashboardLayout from "../layout/DashboardLayout";
import RegionalAnalysisDashboard from "../components/dashboard/RegionalAnalysis";
import ReportListDashboard from "../components/dashboard/ReportList";
import VersionAnalysisDashboard from "../components/dashboard/VersionAnalysis";
import AppStoreIndicatorAnalysis from "../components/dashboard/UniversalLinkAnalysis";
import IndicatorDashboard from "../components/dashboard/IndicatorDashboard";
import UserSignUpGraphDashboard from "../components/dashboard/UserSignUpGraphDashboard";


function Dashboard() {
    return (
        <>
            <DashboardLayout
                indicatorContentList={IndicatorDashboard()}
                graphContent={UserSignUpGraphDashboard()}
                graphContent2={RegionalAnalysisDashboard()}
                basicContent1={ReportListDashboard()}
                basicContent2={VersionAnalysisDashboard()}
                basicContent3={AppStoreIndicatorAnalysis()}
            />
        </>
    );

}

export default Dashboard;
