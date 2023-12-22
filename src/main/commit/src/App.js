import React from 'react';
import {Routes, Route} from "react-router-dom";
import Home from "./router/Home";
import Community from "./router/Community/Community";
import News from "./router/News/News";
import Mypage from "./router/Mypage/Mypage";
import Login from "./router/Member/Login";
import Join from "./router/Member/join";
import FindId from "./router/Member/findid";
import FindIdOk from "./router/Member/findidok";
import FindPw from "./router/Member/findpw";
import Job from "./router/Job/Job";


function App() {
  return (
    <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/News" element={<News/>}/>
        <Route path="/Job" element={<Job/>}/>
        <Route path="/Community" element={<Community/>}/>
        <Route path="/Mypage" element={<Mypage/>}/>
        <Route path="/Login" element={<Login/>}/>
        <Route path="/join" element={<Join/>}/>
        <Route path="/Findid" element={<FindId/>}/>
        <Route path="/Findidok" element={<FindIdOk/>}/>
        <Route path="/Findpw" element={<FindPw/>}/>
      </Routes>
  );
}

export default App;
