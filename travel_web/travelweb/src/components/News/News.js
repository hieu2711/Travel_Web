
import './News.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faThumbsUp } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';
import { useContext, useEffect, useState } from 'react';
import Apis, { authApi, endpoints } from '../../configs/Apis';
import MySpinner from '../../layout/MySpinner';
import { Button } from 'flowbite-react';
import { Form } from 'react-bootstrap';
import Moment from 'react-moment';
import { MyUserContext } from '../../App';

const News = () => {
    const [user,] = useContext(MyUserContext);
    const [news, setNews] = useState(null);
    const [comments, setComments] = useState(null);
    const [newComment, setNewComment] = useState();
    const [isLiked, setIsLiked] = useState({});

    useEffect(() => {
        const fetchData = async () => {
            try {
                // Gọi API để lấy tin tức
                const newsResponse = await Apis.get(endpoints['news']);
                const newsData = newsResponse.data;
                setNews(newsData);

                // Tạo một mảng chứa tất cả các kết quả từ việc gọi API cho từng tin tức
                const commentsData = await Promise.all(
                    newsData.map(async (c) => {
                        try {
                            const response = await Apis.get(endpoints['comments-new'](c.id));
                            return response.data;
                        } catch (error) {
                            // Xử lý lỗi nếu có
                            console.error(`Lỗi khi tải bình luận cho tin tức có ID ${c.id}:`, error);
                            return []; // Hoặc trả về giá trị mặc định cho trường hợp lỗi
                        }
                    })
                );

                // Gộp tất cả các kết quả thành một mảng duy nhất
                const allComments = commentsData.flat();

                // Cập nhật state 'comments' với dữ liệu mới
                setComments(allComments);

                const initialLikes = {};
                newsData.forEach((p) => {
                    initialLikes[p.id] = false;
                });
                setIsLiked(initialLikes);
            } catch (error) {
                console.error('Lỗi khi tải dữ liệu:', error);
            }
        };

        fetchData();
    }, []);

    const handleCommentSubmit = async (newsId) => {
        if (newComment.trim() === "") {
            // Không cho phép gửi bình luận trống
            return;
        }

        try {
            const isLikeValue = isLiked[newsId] ? 1 : 0;
            const commentData = {
                comments: newComment,
                newsId: newsId,
                isLike: isLikeValue,
            };
            let { data } = await authApi().post(endpoints['add-comments-new'], commentData);
            setComments([...comments, data]);
            setNewComment("");
        } catch (error) {
            console.error('Lỗi khi thêm bình luận:', error);
        }
    };
    const handleLikeClick = (newsId) => {
        setIsLiked((prevIsLiked) => ({
            ...prevIsLiked,
            [newsId]: !prevIsLiked[newsId], // Đảo ngược trạng thái "like" cho bài viết cụ thể
        }));
    };
    console.log(isLiked)
    let url2 = `/login?next=/news`;
    if (news === null)
        return <MySpinner />

    return (
        <>
            {news.map((p) => (
                <div className="items" key={p.id}>
                    <img src={p.image} alt={p.title} />
                    <div className="item">
                        <p>
                            {p.title}
                            <FontAwesomeIcon
                                className={`like-icon ${isLiked[p.id] ? 'active' : ''}`}
                                icon={faThumbsUp}
                                onClick={() => handleLikeClick(p.id)}
                                style={{ cursor: 'pointer' }}
                            />
                        </p>
                        <p>{p.content}</p>
                        <p className="cmt">Bình luận</p>
                        {/* Hiển thị bình luận cho tin tức */}
                        {comments !== null && comments.map((comment) => (
                            // Kiểm tra xem comment có thuộc tin tức hiện tại không
                            comment.newsId.id === p.id && (
                                <div key={comment.id} style={{ display: "flex", marginBottom: "10px" }}>
                                    <img
                                        style={{ width: "40px", height: "40px" }}
                                        src={comment.userId.avatar}
                                        className="rounded-circle small-avatar"
                                        alt="Avatar"
                                    />
                                    <div style={{ borderRadius: "20px", padding: "7px", backgroundColor: "#F2F3F5", marginLeft: "-5px" }}>
                                        <p style={{ fontWeight: "bolder", marginBottom: "-3px" }}>{comment.userId.username}</p>
                                        <p style={{ marginBottom: "-3px" }}>{comment.comments}</p>
                                        <Moment style={{ fontSize: "12px" }} locale="vi" fromNow>{comment.createdDate}</Moment>
                                    </div>
                                </div>
                            )
                        ))}
                    </div>
                    {user === null ? <div style={{position:"absolute" , bottom:"0",right:"0",marginRight:"20px"}}><p>Vui lòng <Link to={url2}>đăng nhập</Link> để bình luận! </p></div> : <>
                    <div style={{ display: "flex", justifyContent: "flex-end", position: "absolute", bottom: "0", right: "0" }}>
                        <Button onClick={() => handleCommentSubmit(p.id)} style={{ width: "30%", borderRadius: "10px", fontSize: "12px" }} className="mt-2" variant="info">Bình luận</Button>
                        <Form.Control onChange={(e) => setNewComment(e.target.value)} style={{ width: "388px" }} as="textarea" aria-label="With textarea" placeholder="Nội dung bình luận" />
                    </div>
                    </>}
                </div>
            ))}
        </>
    )
}

export default News;
