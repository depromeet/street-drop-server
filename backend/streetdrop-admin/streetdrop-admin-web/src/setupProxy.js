const {createProxyMiddleware} = require('http-proxy-middleware');

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
}