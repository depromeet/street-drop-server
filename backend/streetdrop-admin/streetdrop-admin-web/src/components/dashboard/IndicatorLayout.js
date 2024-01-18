import React from "react";

function IndicatorLayout({content}) {
    return (
        <div style={{
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            height: '100%',
            padding: '0.8rem 1rem'
        }}>
            <div style={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'flex-end',

                marginLeft: 'auto'
            }}>
                <p style={{
                    color: '#5B5B5B',
                    fontSize: '14px',
                    fontWeight: 600,
                    lineHeight: 'normal',
                    margin: '0',
                    marginBottom: '8px'
                }}>{content.title}</p>
                <p style={{
                    color: '#333',
                    fontSize: '24px',
                    fontWeight: 700,
                    lineHeight: 'normal',
                    margin: '0'
                }}>{content.value}</p>
            </div>
        </div>
    )
}

export default IndicatorLayout;