using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;
using wsUltraList.Interfaces;
using wsUltraList.Modelo;
using wsUltraList.Utils;

namespace wsUltraList.Controllers
{
    public class ArticuloController : GenericController<Articulo>
    {
        protected override Func<Articulo, bool> BuscarEntidad(Articulo entidad)
        {
            throw new NotImplementedException();
        }

        protected override Func<Articulo, bool> BuscarID(int id)
        { return (a) => a.id_articulo == id; }

        [HttpPost]
        public Result<int> CompartirOferta(Articulo articulo)
        {
            try
            {
                var o = new Oferta(articulo);

                if (Oferta.Existe(it => it.id_articulo == articulo.id_articulo))
                {
                    o.id_oferta = Oferta.Obtener(it => it.id_articulo == articulo.id_articulo
                    && it.usuario_agrego == articulo.usuario_agrego).id_oferta;
                    o.fecha_modifico = DateTime.Now;
                    Oferta.Editar(o);
                }
                else
                {
                    o.fecha_agrego = DateTime.Now;
                    Oferta.Agregar(o);
                }

                return new Result<int>(true, o.id_oferta);
            }
            catch (Exception ex)
            { return new Result<int>(false, -1, ex.GetBaseException().Message); }
        }

        [HttpPost]
        public Result<String> EnviarImagen()
        {
            if (HttpContext.Current.Request.Files.AllKeys.Any())
            {
                // Get the uploaded image from the Files collection
                var Imagen = HttpContext.Current.Request.Files["Imagen"];
                var id_oferta = HttpContext.Current.Request.Params.GetValues("id_oferta").FirstOrDefault();

                var oferta = Oferta.Obtener(it => it.id_oferta.ToString() == id_oferta);

                if (Imagen != null && oferta != null)
                {
                    // Validate the uploaded image(optional)
                    oferta.image_path = Imagen.FileName;
                    Oferta.Editar(oferta);

                    string CarpetaDescargas = HttpContext.Current.Server.MapPath("~/UploadedFiles");

                    if (!Directory.Exists(CarpetaDescargas))
                        Directory.CreateDirectory(CarpetaDescargas);

                    // Get the complete file path
                    var fileSavePath = Path.Combine(CarpetaDescargas, Imagen.FileName);

                    // Save the uploaded file to "UploadedFiles" folder
                    Imagen.SaveAs(fileSavePath);

                    return new Result<string>(true, Imagen.FileName);
                }
                else
                    return new Result<String>(false, null, "Ni la imagen ni el id_oferta pueden ser nulos.");
            }
            else
                return new Result<String>(false, null, "Faltan Parametros");
        }
    }
}
