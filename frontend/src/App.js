// src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import BoardList from './components/BoardList';
import BoardForm from './components/BoardForm';
import BoardDetail from './components/BoardDetail'; // 🆕 추가

function App() {
  return (
    <Router>
      <div style={{ padding: '20px' }}>
        {/* 상단 공통 네비게이션 바 */}
        <nav style={{ marginBottom: '20px', borderBottom: '1px solid #ccc', paddingBottom: '10px' }}>
          <Link to="/" style={{ marginRight: '15px', fontWeight: 'bold' }}>글 목록</Link>
          <Link to="/write" style={{ fontWeight: 'bold' }}>글 쓰기</Link>
        </nav>

        {/* 주소에 따라 바뀌는 화면 영역 */}
        <Routes>
          <Route path="/" element={<BoardList />} />
          <Route path="/write" element={<BoardForm />} />
 
 <Route path="/boards/:id" element={<BoardDetail />} /> {/* 🆕 :id 동적 파라미터 추가 */}          
 
        </Routes>
      </div>
    </Router>
  );
}

export default App;
