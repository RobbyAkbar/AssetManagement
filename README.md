# AssetManagement
Test Mobile QTI

## Tech Spec
- Halaman Login:
    * Halaman Login muncul ketika user belum login
    * Validasi email dan password harus di isi
    * Input password harus hidden
    * Terdapat message ketika password atau email salah
    * Ketika login berhasil pindah ke halaman home


- Halaman Home:
    * Halaman Home muncul ketika user sudah login
    * Terdapat detail user (username dan email) foto bisa mengambil dari figma atau random image
    * User bisa logout dan ketika logout berhasil pindah ke halaman login
    * Bisa menampilkan chart status
    * Bisa menampilkan chart lokasi


- Halaman List Asset:
    * Implementasi scroll menggunakan pagination query_params page & page_size pada api/asset
    * Implementasi search menggunakan query_params search pada api /asset
    * Pindah ke halaman input asset ketika tombol tambah di klik
    * Pindah ke halaman edit asset ketika tombol pena di klik


- Halaman Input Asset:
    * Muncul ketika klik tombol tambah biru pada halaman List asset atau Home
    * Tambah validasi aset name tidak boleh kosong (tampilan error bisa dilihat di figma)
    * Tambah validasi status tidak boleh kosong (tampilan error bisa dilihat di figma)
    * Asset berhasil dibuat ketika mengklik submit dan validasi berhasil


- Halaman Edit asset:
    * Muncul ketika klik icon pena pada halaman list asset
    * Ketika di klik muncul detail asset yang di pilih
    * Tambah validasi aset name tidak boleh kosong (tampilan error bisa dilihat di figma)
    * Tambah validasi status tidak boleh kosong (tampilan error bisa dilihat di figma)
    * Asset berhasil diedit ketika mengklik save update dan validasi berhasil
    * Asset berhasil dihapus ketika mengklik delete