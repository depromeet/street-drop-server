# React Build Stage
FROM node:20.0-alpine as build-stage
ENV REACT_APP_ALLOW ALLOW

ENV REACT_APP_BASE_URL https://admin-server.street-drop.com
ENV REACT_APP_API_URL /api
ENV REACT_APP_SEARCH_URL=/search
ENV REACT_APP_NODE_ENV=prod

WORKDIR /app/streetdrop-admin-web

COPY streetdrop-admin-web/package*.json ./
COPY streetdrop-admin-web/ ./

RUN npm install
RUN npm run build


# Node App Production Stage
FROM node:20.0-alpine as production-stage

WORKDIR /app/streetdrop-admin-web-server

COPY streetdrop-admin-web-server/package*.json ./
COPY streetdrop-admin-web-server/ ./
COPY --from=build-stage /app/streetdrop-admin-web/build ./build

RUN npm install

EXPOSE 3030

CMD ["npm", "start"]
