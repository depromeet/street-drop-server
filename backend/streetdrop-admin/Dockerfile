# React Build Stage
FROM node:20.0-alpine as build-stage

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
