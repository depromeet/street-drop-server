const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
    app.use('/search', createProxyMiddleware({
        target: 'https://search.street-drop.com',
        changeOrigin: true,
        pathRewrite: {
            '^/search': ''
        }
    }));

    app.use('/api', createProxyMiddleware({
        target: 'https://api.street-drop.com',
        changeOrigin: true,
        pathRewrite: {
            '^/api': ''
        }
    }));

    // TODO: Localhost CORS 해결용. 추후 Street Drop Admin Server로 변경 필요
    app.use('/admin', createProxyMiddleware({
        target: 'http://localhost:8080',
        changeOrigin: true,
        pathRewrite: {
            '^/admin': ''
        }
    }));
}