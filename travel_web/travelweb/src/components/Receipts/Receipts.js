import { useContext, useEffect, useState } from "react";
import { Alert, Button, Container, Form } from "react-bootstrap";
import { Link, useNavigate  , useParams } from "react-router-dom";
import { MyUserContext } from "../../App";
import Apis, { authApi, endpoints } from "../../configs/Apis";
import MySpinner from "../../layout/MySpinner";


const Receipts = () => {
    const { toursId } = useParams();
    const [user,] = useContext(MyUserContext);
    const [err, setErr] = useState(null);
    const [priceAdult, setPriceAdult] = useState("");
    const [priceChild, setPriceChild] = useState("");
    const [numAdults, setNumAdults] = useState(0);
    const [numChildren, setNumChildren] = useState(0);
    const [totalPayment, setTotalPayment] = useState(0);
    const [product, setProduct] = useState(null);
    const navigate = useNavigate();
    let url2 = `/login?next=/tours/receipts/${toursId}`;
    const [customer, setCustomer] = useState({
        name: "",
        phonenumber: "",
        identification: "",
        email: "",
        address: "",
    });
    const [receipts, setReceipts] = useState({
        "amountPrice": totalPayment,
        "adult": "",
        "child": "",
        "tourId": toursId,
        "userId": user ? user.id : null
    });
    console.log(numAdults)
    console.log(numChildren)
    console.log(totalPayment)
    const change = (evt, field) => {
        setReceipts(current => {
            return { ...current, [field]: evt.target.value };
        });
    }
    const Receipts = (evt) => {
        evt.preventDefault();

        const process = async () => {
            let formData = new FormData();
            for (let field in receipts)
                formData.append(field, receipts[field]);
            try {
                let res = await authApi().post(endpoints['addReceipts'], formData);
                if (res.status === 201) {
                    alert("Bạn đã thanh toán thành công");
                    navigate(-1);
                } else {
                    setErr("Hệ thống đang bị lỗi!");
                }
            } catch (error) {
                setErr("Hệ thống đang bị lỗi!");
                console.log(error);
            }
        }

        process();
    };

    console.log(receipts)

    useEffect(() => {
        setReceipts((prevReceipts) => ({
            ...prevReceipts,
            "amountPrice": totalPayment,
        }));
    }, [totalPayment]);

    useEffect(() => {
        const loadCus = async () => {
            try {
                if (user) {
                    // Nếu user đã đăng nhập, tải thông tin khách hàng từ API
                    const response = await Apis.get(endpoints['info-cus'](user.id));
                    const data = response.data;
                    setCustomer(data);

                }
            } catch (error) {
                console.error("Lỗi khi tải thông tin khách hàng:", error);
            }
        }
        loadCus();
    }, [user]);
    useEffect(() => {
        const loadProduct = async () => {
            try {
                const { data } = await Apis.get(endpoints['details'](toursId));
                setProduct(data);
                const priceAdult = data.priceId.priceAdult;
                setPriceAdult(priceAdult)
                const priceChild = data.priceId.priceChild;
                setPriceChild(priceChild)
            } catch (error) {
                console.error("Lỗi khi tải thông tin sản phẩm:", error);
            }
        }
        loadProduct();
    }, [toursId]);

    const calculateTotalPayment = () => {
        const total = (numAdults * priceAdult) + (numChildren * priceChild);
        setTotalPayment(total);
    };
    const formatPriceAdult = priceAdult.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
    const formatPriceChild = priceChild.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
    console.log(formatPriceAdult)
    if (product === null)
        return <MySpinner />;
    return (
        <Container>
            {user ? (
                <Form onSubmit={Receipts} style={{ border: "1px solid #ccc", borderRadius: "10px" }}>
                    <h1>Thanh toán</h1>
                    <Form.Group>
                        <Form.Label>Tên khách hàng</Form.Label>
                        <Form.Control value={customer.name} style={{ marginLeft: "90px", width: "70%" }} type="text" placeholder="Tên khách hàng" name="name" required />
                    </Form.Group>
                    <div className="d-flex" style={{ marginLeft: "200px" }}>
                        <Form.Label >Số điện thoại</Form.Label>
                        <Form.Control value={customer.phonenumber} style={{ margin: "0", padding: "0", marginTop: "-5px", width: "220px", marginLeft: "10px" }} type="text" placeholder="Số điện thoại" name="phone" required />
                        <Form.Label style={{ marginLeft: "30px" }}>CMND</Form.Label>
                        <Form.Control value={customer.identification} style={{ margin: "0", padding: "0", marginTop: "-5px", width: "230px", marginLeft: "10px" }} type="text" placeholder="CMND" name="identification" required />
                    </div>

                    <Form.Group style={{ display: "flex", alignItems: "center", marginTop: "40px" }} >
                        <Form.Label style={{ marginRight: "160px" }}>Email</Form.Label>
                        <Form.Control value={customer.email} style={{ margin: "0", width: "70%" }} type="email" placeholder="Email" name="email" required />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Địa chỉ</Form.Label>
                        <Form.Control value={customer.address} style={{ marginLeft: "150px", width: "70%" }} type="text" placeholder="Địa chỉ" name="address" required />
                    </Form.Group>
                    <div className="d-flex" style={{ marginLeft: "200px", marginBottom: "30px" }}>
                        <Form.Label >Giá người lớn</Form.Label>
                        <Form.Control value={formatPriceAdult} style={{ margin: "0", padding: "0", marginTop: "-5px", width: "220px", marginLeft: "10px" }} type="text" placeholder="Giá người lớn" name="priceAdult" required />
                        <Form.Label style={{ marginLeft: "30px" }}>Giá trẻ em</Form.Label>
                        <Form.Control value={formatPriceChild} style={{ margin: "0", padding: "0", marginTop: "-5px", width: "230px", marginLeft: "10px" }} type="text" placeholder="Giá trẻ em" name="priceChild" required />
                    </div>
                    <div className="d-flex" style={{ marginLeft: "200px" }}>
                        <Form.Label >Số người lớn</Form.Label>
                        <Form.Control onChange={(e) => {setNumAdults(parseInt(e.target.value, 10));change(e, "adult")}} style={{ margin: "0", padding: "0", marginTop: "-5px", width: "220px", marginLeft: "15px" }} type="number" placeholder="Số người lớn" name="Adult" required />
                        <Form.Label style={{ marginLeft: "30px" }}>Số trẻ em</Form.Label>
                        <Form.Control onChange={(e) => {setNumChildren(parseInt(e.target.value, 10));change(e, "child")}} style={{ margin: "0", padding: "0", marginTop: "-5px", width: "230px", marginLeft: "15px" }} type="number" placeholder="Số trẻ em" name="Child" required />
                    </div>
                    <Button onClick={calculateTotalPayment} className="btn btn-info mt-3" style={{ width: "15%", marginLeft: "380px" }} >Tổng thanh toán</Button>
                    <Form.Control onChange={(e) => change(e, "amountPrice")} value={totalPayment.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })} style={{ padding: "0", marginTop: "40px", marginRight: "20px", width: "220px", marginLeft: "20px" }} type="text" placeholder="Tổng thanh toán" name="phone" required />
                    <Form.Group className="mb-3">
                        <Button style={{ width: "50%", marginLeft: "300px", fontSize: "20px" }} variant="info" type="submit">Thanh toán hóa đơn</Button>
                    </Form.Group>
                </Form>
            ) : (
                <p>Vui lòng <Link to={url2}>đăng nhập</Link> để thanh toán!</p>
            )}
        </Container>
    )
}

export default Receipts;