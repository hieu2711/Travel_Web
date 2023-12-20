
import { Footer } from 'flowbite-react';
import {  BsFacebook, BsGithub, BsInstagram, BsTwitter } from 'react-icons/bs';

export default function FooterWithSocialMediaIcons() {
  return (
    <Footer container className="footer-container">
      <div className="w-full" style={{marginTop:"40px"}}>
        <div className="grid w-full justify-between sm:flex sm:justify-between md:flex md:grid-cols-1" >

          <div className="grid grid-cols-2 gap-8 sm:mt-4 sm:grid-cols-3 sm:gap-6" style={{display:'flex',justifyContent:'space-around'}}>
          <div>
            <Footer.Brand
            style={{fontSize:'30px'}}
              href="#"
              name="Travel_Website"
            />
             
          </div>
            <div>
              <Footer.Title title="Về chúng tôi" />
              <Footer.LinkGroup col>
                <Footer.Link href="#" style={{textDecoration:'none',color:'black'}}>
                  Giới thiệu
                </Footer.Link>
                <Footer.Link href="#" style={{textDecoration:'none',color:'black'}}>
                Tours du lịch
                </Footer.Link>
                <Footer.Link href="#" style={{textDecoration:'none',color:'black'}}>
                Chính sách 
                </Footer.Link>
              </Footer.LinkGroup>
            </div>
            <div>
              <Footer.Title title="Liên hệ với chung tôi" />
            <div style={{display:'flex', justifyContent:'center',marginLeft:'-70px',marginBottom:'5px'}}>
            <Footer.Icon
              href="#"
              icon={BsFacebook}/>
            <Footer.Icon
              href="#"
              icon={BsInstagram}
              style={{marginLeft:'10px'}}
            />
            <Footer.Icon
              href="#"
              icon={BsTwitter}
              style={{marginLeft:'10px'}}
            />
            <Footer.Icon
              href="#"
              icon={BsGithub}
              style={{marginLeft:'10px'}}
            />
            </div>
          <Footer.Title title="Liên hệ: 0378725917"  style={{fontSize:'20px',marginLeft:'60px'}}/>
          <Footer.Title title="Địa chỉ: 42/6 Văn Chung, phường 13,quận Tân Bình,Tp Hồ Chí Minh"  style={{fontSize:'13px',marginLeft:'-30px'}}/>
            </div>
          </div>
        </div>
        <Footer.Divider />
        <div className="w-full sm:flex sm:items-center sm:justify-between" style={{display:'flex', justifyContent:'center'}}>
          <Footer.Copyright
            by="TranDucHieu "
            href="#"
            year={2023 }
          />
        </div>
      </div>
    </Footer>
  )
}


