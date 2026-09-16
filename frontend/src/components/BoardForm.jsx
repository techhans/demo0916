// BoardForm.jsx
import React, { useState } from 'react';
import axios from 'axios';

function BoardForm() {
  const [board, setBoard] = useState({
    title: '',
    content: '',
    writer: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBoard({
      ...board,
      [name]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      // 스프링 부트 서버로 POST 요청 전송
      const response = await axios.post('http://localhost:8080/api/boards', board);
// 기존 코드
// console.log('저장 성공:', response.data);
// 아래처럼 변경 (공통 메시지 사용 가능)
console.log('저장 성공:', response.data.data);
alert(response.data.message); // 스프링이 보낸 "게시글이 성공적으로 등록되었습니다."가 뜸!

      alert('게시글이 성공적으로 등록되었습니다!');
      
      // 입력 폼 초기화
      setBoard({ title: '', content: '', writer: '' });
    } catch (error) {
      console.error('저장 실패:', error);
      alert('데이터 전송 중 오류가 발생했습니다.');
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '400px' }}>
      <h2>게시글 작성</h2>
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '10px' }}>
          <label>제목: </label>
          <input 
            type="text" 
            name="title" 
            value={board.title} 
            onChange={handleChange} 
            required 
          />
        </div>
        <div style={{ marginBottom: '10px' }}>
          <label>작성자: </label>
          <input 
            type="text" 
            name="writer" 
            value={board.writer} 
            onChange={handleChange} 
            required 
          />
        </div>
        <div style={{ marginBottom: '10px' }}>
          <label>내용: </label>
          <textarea 
            name="content" 
            value={board.content} 
            onChange={handleChange} 
            required 
          />
        </div>
        <button type="submit">저장하기</button>
      </form>
    </div>
  );
}

export default BoardForm;
