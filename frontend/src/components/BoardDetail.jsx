// src/components/BoardDetail.js
import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

function BoardDetail() {
  const { id } = useParams();
  const navigate = useNavigate(); // 삭제 후 목록으로 이동하기 위해 사용
  const [board, setBoard] = useState(null);
  
  // 수정 모드 상태 관리 (true면 입력창이 보임)
  const [isEditMode, setIsEditMode] = useState(false);
  const [editForm, setEditForm] = useState({ title: '', content: '' });

  // 데이터 로딩 함수
  const fetchBoard = () => {
    axios.get(`http://localhost:8080/api/boards/${id}`)
      .then(response => {
        setBoard(response.data.data);
        setEditForm({
          title: response.data.data.title,
          content: response.data.data.content
        });
      })
      .catch(error => {
        console.error("상세 정보 로딩 실패:", error);
        alert("게시글을 불러올 수 없습니다.");
      });
  };

  useEffect(() => {
    fetchBoard();
  }, [id]);

  // 수정 입력값 변경 핸들러
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEditForm({ ...editForm, [name]: value });
  };

  // 1. 수정 완료 (PUT 요청)
  const handleUpdate = async () => {
    try {
      const response = await axios.put(`http://localhost:8080/api/boards/${id}`, editForm);
      alert(response.data.message);
      setIsEditMode(false);
      fetchBoard(); // 수정된 내용으로 화면 새로고침
    } catch (error) {
      console.error("수정 실패:", error);
      alert("수정 중 오류가 발생했습니다.");
    }
  };

  // 2. 삭제 진행 (DELETE 요청)
  const handleDelete = async () => {
    if (!window.confirm("정말로 이 게시글을 삭제하시겠습니까?")) return;

    try {
      const response = await axios.delete(`http://localhost:8080/api/boards/${id}`);
      alert(response.data.message);
      navigate('/'); // 삭제 성공 후 메인 목록 페이지로 이동
    } catch (error) {
      console.error("삭제 실패:", error);
      alert("삭제 중 오류가 발생했습니다.");
    }
  };

  if (!board) return <div style={{ padding: '20px' }}>로딩 중...</div>;

  return (
    <div style={{ padding: '20px', maxWidth: '600px' }}>
      <h2>게시글 상세 보기</h2>
      
      <div style={{ border: '1px solid #ccc', padding: '15px', borderRadius: '5px', marginBottom: '15px' }}>
        <p><strong>번호:</strong> {board.id}</p>
        <p><strong>작성자:</strong> {board.writer}</p>
        <hr />

        {isEditMode ? (
          /* ✏️ 수정 모드일 때 보여주는 화면 */
          <div>
            <div style={{ marginBottom: '10px' }}>
              <label><strong>제목: </strong></label>
              <input 
                type="text" 
                name="title" 
                value={editForm.title} 
                onChange={handleInputChange} 
                style={{ width: '90%', padding: '5px' }}
              />
            </div>
            <div>
              <label><strong>내용: </strong></label>
              <textarea 
                name="content" 
                value={editForm.content} 
                onChange={handleInputChange} 
                rows="5"
                style={{ width: '90%', padding: '5px' }}
              />
            </div>
          </div>
        ) : (
          /* 📄 일반 보기 모드일 때 보여주는 화면 */
          <div>
            <p><strong>제목:</strong> {board.title}</p>
            <p style={{ whiteSpace: 'pre-wrap' }}><strong>내용:</strong><br />{board.content}</p>
          </div>
        )}
      </div>

      {/* 버튼 영역 */}
      <div>
        {isEditMode ? (
          <>
            <button onClick={handleUpdate} style={{ marginRight: '10px', backgroundColor: '#4CAF50', color: 'white' }}>저장</button>
            <button onClick={() => setIsEditMode(false)} style={{ marginRight: '10px' }}>취소</button>
          </>
        ) : (
          <>
            <button onClick={() => navigate('/')} style={{ marginRight: '10px' }}>목록으로</button>
            <button onClick={() => setIsEditMode(true)} style={{ marginRight: '10px', backgroundColor: '#FF9800', color: 'white' }}>수정하기</button>
            <button onClick={handleDelete} style={{ backgroundColor: '#f44336', color: 'white' }}>삭제하기</button>
          </>
        )}
      </div>
    </div>
  );
}

export default BoardDetail;