// src/components/BoardList.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

function BoardList() {
  const [boards, setBoards] = useState([]);

  // 페이지가 열릴 때 스프링에서 목록을 받아옵니다.
  useEffect(() => {
    axios.get('http://localhost:8080/api/boards')
// 기존 코드
// .then(response => setBoards(response.data))
// 아래처럼 변경
.then(response => setBoards(response.data.data))
      .catch(error => console.error("목록 로딩 실패:", error));
  }, []);

  return (
    <div style={{ padding: '20px' }}>
      <h2>게시글 목록</h2>
      <Link to="/write"><button style={{ marginBottom: '15px' }}>글쓰기 가기</button></Link>
      
      <table border="1" cellPadding="10" style={{ width: '100%', borderCollapse: 'collapse' }}>
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
          </tr>
        </thead>
<tbody>
  {boards.map((board) => (
    <tr key={board.id}>
      <td>{board.id}</td>
      <td>
        {/* 제목을 누르면 /boards/id 경로로 이동합니다 */}
        <Link to={`/boards/${board.id}`} style={{ textDecoration: 'none', color: 'blue' }}>
          {board.title}
        </Link>
      </td>
      <td>{board.writer}</td>
    </tr>
  ))}
</tbody>
      </table>
    </div>
  );
}

export default BoardList;
