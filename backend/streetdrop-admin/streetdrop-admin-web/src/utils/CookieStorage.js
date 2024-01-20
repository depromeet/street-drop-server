
const cookieStorage ={
    get(key){
        const cookies = document.cookie.split(';')
        for(let i=0;i<cookies.length;i++){
            const cookie = cookies[i].split('=')
            if(cookie[0].trim() === key){
                return cookie[1]
            }
        }
        return null
    },
    set(key,value){
        document.cookie = `${key}=${value}  HttpOnly`
    },
    remove(key){
        document.cookie = `${key}=;max-age=0  HttpOnly`
    }
}

export default cookieStorage;