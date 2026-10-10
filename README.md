# 🧶 CozyCrafts Studio — Gestiune magazin online

**CozyCrafts Studio** este un proiect realizat pentru disciplina **Proiectarea și Programarea Orientată pe Obiecte (PPOO)**. Aplicația este dezvoltată în Java și își propune să gestioneze, la nivel de consolă, produsele și stocurile unui mic magazin de creații handmade, de la articole croșetate până la kituri de lucru manual.

Ideea din spatele proiectului a pornit din dorința de a modela un magazin simplu și intuitiv, aplicând conceptele învățate la curs și seminar: clase și obiecte, moștenire, polimorfism, colecții și lucrul cu fișiere pentru păstrarea datelor.

---

### Ce face aplicația?
* **Operații de bază (CRUD)**: Permite adăugarea de produse noi, vizualizarea și căutarea în catalog, modificarea prețurilor sau a stocurilor și ștergerea articolelor.
* **Persistența datelor**: Toate modificările se fac în memorie pe parcursul rulării, iar la închiderea programului datele se salvează automat în fișier (fără să fie nevoie de o bază de date externă), fiind restaurate la următoarea pornire.
* **Structuri de date**: Folosește colecții Java (`ArrayList`, `HashMap`) pentru gestionarea produselor și masive de date fundamentale (vectori și matrici) pentru evidența vânzărilor lunare și a organizării pe rafturi.
* **Rapoarte și statistici**: Poate genera un fișier text cu un rezumat al stocurilor, produse care necesită reaprovizionare și statistici de vânzări.
* **Validări și excepții**: Datele introduse de la tastatură sau citite din fișiere sunt validate atent, folosind excepții dedicate pentru a evita erorile la rulare.
