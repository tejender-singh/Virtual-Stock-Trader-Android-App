package com.example.jeevika.materialdesign;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Trade extends Fragment {
    final Context context1 = getActivity();
    EditText ed3,ed4;
    private ProgressBar spinner;
    private ProgressBar spinnerzoom;
    AutoCompleteTextView name;
    RadioGroup stockGroup;
    String stocks[]={"SYMBOL","20MICRONS","3IINFOTECH","3MINDIA","3RDROCK","8KMILES","A2ZINFRA","AARTIDRUGS","AARTIIND","AARVEEDEN","ABAN","ABB","ABBOTINDIA","ABCIL","ABGSHIP","ABIRLANUVO","ACC","ACCELYA","ACE","ACROPETAL","ADANIENT","ADANIPORTS","ADANIPOWER","ADANITRANS","ADFFOODS","ADHUNIK","ADI","ADLABS","ADORWELD","ADSL","ADVANIHOTR","ADVANTA","AEGISCHEM","AFL","AGARIND","AGCNET","AGRITECH","AGRODUTCH","AHLEAST","AHLUCONT","AHLWEST","AIAENG","AIL","AJANTPHARM","AJMERA","AKSHOPTFBR","AKZOINDIA","ALANKIT","ALBK","ALCHEM","ALEMBICLTD","ALICON","ALKALI","ALKYLAMINE","ALLCARGO","ALLSEC","ALMONDZ","ALOKTEXT","ALPA","ALPHAGEO","ALPSINDUS","ALSTOMT&D","AMARAJABAT","AMBIKCO","AMBUJACEM","AMDIND","AMRUTANJAN","AMTEKAUTO","AMTL","ANANTRAJ","ANDHRABANK","ANDHRACEMT","ANDHRSUGAR","ANGIND","ANIKINDS","ANKITMETAL","ANSALAPI","ANSALHSG","ANTGRAPHIC","APARINDS","APCOTEXIND","APLAPOLLO","APLLTD","APOLLOHOSP","APOLLOTYRE","APOLSINHOT","APTECHT","ARCHIDPLY","ARCHIES","ARCOTECH","ARIES","ARIHANT","AROGRANITE","ARROWCOAT","ARROWTEX","ARSHIYA","ARSSINFRA","ARVIND","ARVINDREM","ASAHIINDIA","ASAHISONG","ASAL","ASHAPURMIN","ASHIANA","ASHIMASYN","ASHOKA","ASHOKLEY","ASIANHOTNR","ASIANPAINT","ASIANTILES","ASSAMCO","ASTEC","ASTRAL","ASTRAMICRO","ASTRAZEN","ATFL","ATLANTA","ATLASCYCLE","ATNINTER","ATUL","ATULAUTO","AURIONPRO","AUROPHARMA","AUSOMENT","AUSTRAL","AUTOAXLES","AUTOIND","AUTOLITIND","AVANTIFEED","AVTNPL","AXISBANK","AXISCADES","AXISGOLD","BAFNAPHARM","BAGFILMS","BAJAJ-AUTO","BAJAJCORP","BAJAJELEC","BAJAJFINSV","BAJAJHIND","BAJAJHLDNG","BAJFINANCE","BALAJITELE","BALAMINES","BALKRISIND","BALLARPUR","BALMLAWRIE","BALPHARMA","BALRAMCHIN","BANARBEADS","BANARISUG","BANCOINDIA","BANKBARODA","BANKBEES","BANKINDIA","BANSWRAS","BARTRONICS","BASF","BASML","BATAINDIA","BAYERCROP","BBL","BBTC","BEARDSELL","BEDMUTHA","BEL","BEML","BEPL","BERGEPAINT","BFINVEST","BFUTILITIE","BGLOBAL","BGRENERGY","BHAGYNAGAR","BHARATFORG","BHARATGEAR","BHARATRAS","BHARTIARTL","BHEL","BHUSANSTL","BIL","BILENERGY","BILPOWER","BINANIIND","BINDALAGRO","BIOCON","BIRLACORPN","BIRLACOT","BIRLAERIC","BIRLAMONEY","BLBLIMITED","BLISSGVS","BLKASHYAP","BLUECHIP","BLUEDART","BLUESTARCO","BLUESTINFO","BODALCHEM","BOMDYEING","BOSCHLTD","BPCL","BPL","BRFL","BRIGADE","BRITANNIA","BROOKS","BSELINFRA","BSL","BSLGOLDETF","BSLIMITED","BSLNIFTY","BURNPUR","BUTTERFLY","BVCL","BYKE","CADILAHC","CAIRN","CAMLINFINE","CANBK","CANDC","CANFINHOME","CANTABIL","CAPF","CAPLIPOINT","CARBORUNIV","CAREERP","CARERATING","CASTEXTECH","CASTROLIND","CCCL","CCHHL","CCL","CEATLTD","CEBBCO","CELEBRITY","CELESTIAL","CENTENKA","CENTEXT","CENTRALBK","CENTUM","CENTURYPLY","CENTURYTEX","CERA","CEREBRAINT","CESC","CGCL","CHAMBLFERT","CHEMFALKAL","CHENNPETRO","CHOLAFIN","CHROMATIC","CIGNITITEC","CIMMCO","CINELINE","CINEVISTA","CIPLA","CLASSIC","CLNINDIA","CMAHENDRA","CMC","CNOVAPETRO","COALINDIA","COLPAL","COMPUSOFT","CONCOR","CONSOFINVT","CORDSCABLE","COREEDUTEC","COROMANDEL","CORPBANK","COSMOFILMS","COUNCODOS","COX&KINGS","CPSEETF","CREATIVEYE","CREST","CRISIL","CRMFGETF","CROMPGREAV","CTE","CUB","CUBEXTUB","CUMMINSIND","CURATECH","CYBERTECH","CYIENT","DAAWAT","DABUR","DALMIABHA","DALMIASUG","DATAMATICS","DBCORP","DBREALTY","DBSTOCKBRO","DCBBANK","DCM","DCMSHRIRAM","DCW","DECCANCE","DEEPAKFERT","DEEPAKNTR","DEEPIND","DELTACORP","DELTAMAGNT","DEN","DENABANK","DENORA","DHAMPURSUG","DHANBANK","DHANUKA","DHARSUGAR","DHFL","DHUNINV","DIAPOWER","DICIND","DIGJAM","DISHMAN","DISHTV","DIVISLAB","DLF","DLINKINDIA","DOLPHINOFF","DONEAR","DPL","DPSCLTD","DQE","DREDGECORP","DRREDDY","DSKULKARNI","DSSL","DTIL","DUNCANSLTD","DWARKESH","DYNAMATECH","DYNATECH","EASTSILK","EASUNREYRL","ECEIND","ECLERX","ECLFINANCE","ECLFINANCE","ECLFINANCE","ECLFINANCE","ECLFINANCE","EDELWEISS","EDL","EDUCOMP","EICHERMOT","EIDPARRY","EIHAHOTELS","EIHOTEL","EIMCOELECO","EKC","ELAND","ELDERPHARM","ELECON","ELECTCAST","ELECTHERM","ELGIEQUIP","ELGIRUBCO","EMAMIINFRA","EMAMILTD","EMCO","EMKAY","EMMBI","ENERGYDEV","ENGINERSIN","ENIL","ENTEGRA","EON","ERAINFRA","EROSMEDIA","ESABINDIA","ESCORTS","ESL","ESSAROIL","ESSARPORTS","ESSARSHPNG","ESSDEE","ESSELPACK","ESTER","EUROCERA","EUROMULTI","EUROTEXIND","EVEREADY","EVERESTIND","EVERONN","EXCEL","EXCELCROP","EXCELINDUS","EXIDEIND","FACT","FAGBEARING","FARMAXIND","FCEL","FCL","FCSSOFT","FDC","FEDDERLOYD","FEDERALBNK","FIEMIND","FILATEX","FINANTECH","FINCABLES","FINPIPE","FLEXITUFF","FLFL","FMGOETZE","FMNL","FORTIS","FOSECOIND","FRL","FRLDVR","FSL","GABRIEL","GAEL","GAIL","GAL","GALLANTT","GALLISPAT","GAMMNINFRA","GAMMONIND","GANDHITUBE","GANECOS","GANESHHOUC","GANGOTRI","GARDENSILK","GARWALLROP","GATI","GAYAPROJ","GDL","GEECEE","GEINDSYS","GEMINI","GENESYS","GENUSPAPER","GENUSPOWER","GEOJITBNPP","GEOMETRIC","GESHIP","GHCL","GICHSGFIN","GILLANDERS","GILLETTE","GINNIFILA","GIPCL","GISOLUTION","GITANJALI","GKWLIMITED","GLAXO","GLENMARK","GLFL","GLOBALVECT","GLOBOFFS","GLOBUSSPR","GMBREW","GMDCLTD","GMRINFRA","GNFC","GOACARBON","GODFRYPHLP","GODREJCP","GODREJIND","GODREJPROP","GOENKA","GOKEX","GOKUL","GOLDBEES","GOLDENTOBC","GOLDIAM","GOLDINFRA","GOLDSHARE","GOLDTECH","GOODLUCK","GPIL","GPPL","GRANULES","GRAPHITE","GRASIM","GRAVITA","GREAVESCOT","GREENFIRE","GREENLAM","GREENPLY","GREENPOWER","GRINDWELL","GRPLTD","GRUH","GSCLCEMENT","GSFC","GSKCONS","GSPL","GSS","GTL","GTLINFRA","GTNIND","GTNTEX","GTOFFSHORE","GUFICBIO","GUJALKALI","GUJAPOLLO","GUJFLUORO","GUJNRECOKE","GUJNREDVR","GULFCORP","GULFOILLUB","GULFPETRO","GULPOLY","GVKPIL","HANUNG","HARITASEAT","HARRMALAYA","HATHWAY","HATSUN","HAVELLS","HBLPOWER","HBSTOCK","HCC","HCIL","HCL-INSYS","HCLTECH","HDFC","HDFCBANK","HDFCBANK","HDFCMFGETF","HDIL","HEG","HEIDELBERG","HELIOSMATH","HERCULES","HERITGFOOD","HEROMOTOCO","HESTERBIO","HEXATRADEX","HEXAWARE","HFCL","HGS","HIKAL","HIL","HILTON","HIMATSEIDE","HINDALCO","HINDCOMPOS","HINDCOPPER","HINDDORROL","HINDMOTORS","HINDNATGLS","HINDOILEXP","HINDPETRO","HINDSYNTEX","HINDUJAFO","HINDUJAVEN","HINDUNILVR","HINDZINC","HIRECT","HITACHIHOM","HITECHGEAR","HITECHPLAS","HMT","HMVL","HNGSNGBEES","HOCL","HONAUT","HONDAPOWER","HOTELEELA","HOVS","HSIL","HTMEDIA","HUBTOWN","HUDCO","HUDCO","HUDCO","IBREALEST","IBULHSGFIN","IBVENTURES","IBWSL","ICICIBANK","ICIL","ICNX100","ICRA","ICSA","IDBI","IDBIGOLD","IDEA","IDFC","IDFC","IDR1AGD","IFBAGRO","IFBIND","IFCI","IFCI","IFCI","IFGLREFRAC","IGARASHI","IGL","IGOLD","IGPL","IIFL","IIFLFIN","IIFLFIN","IIFLFIN","IIFLFIN","IIFLFIN","IIFLFIN","IIFLFIN","IIHFL","IIHFL","IITL","IL&FSENGG","IL&FSTRANS","IMFA","IMPAL","IMPEXFERRO","INDBANK","INDHOTEL","INDHOTEL","INDIACEM","INDIAGLYCO","INDIANB","INDIANCARD","INDIANHUME","INDLMETER","INDNIPPON","INDOCO","INDORAMA","INDOSOLAR","INDOTECH","INDOTHAI","INDOWIND","INDRAMEDCO","INDSWFTLAB","INDSWFTLTD","INDTERRAIN","INDUSINDBK","INFINITE","INFOMEDIA","INFRABEES","INFRATEL","INFY","INGERRAND","INIFTY","INNOIND","INOXLEISUR","INOXWIND","INSECTICID","INTELLECT","INVENTURE","IOB","IOC","IOLCP","IPAPPM","IPCALAB","IRB","IREDA","IREDA","IRFC","IRFC","IRFC","IRFC","IRFC","IRFC","ISFT","ISMTLTD","ITC","ITDCEM","ITI","IVC","IVP","IVRCLINFRA","IZMO","J&KBANK","JAGRAN","JAGSNPHARM","JAIBALAJI","JAICORPLTD","JAIHINDPRO","JAINSTUDIO","JAMNAAUTO","JAYAGROGN","JAYBARMARU","JAYNECOIND","JAYSREETEA","JBCHEPHARM","JBFIND","JBMA","JCTEL","JENSONICOL","JETAIRWAYS","JINDALPHOT","JINDALPOLY","JINDALSAW","JINDALSTEL","JINDCOT","JINDRILL","JINDWORLD","JISLDVREQS","JISLJALEQS","JKCEMENT","JKIL","JKLAKSHMI","JKPAPER","JKTYRE","JMA","JMCPROJECT","JMFINANCIL","JMTAUTOLTD","JOCIL","JPASSOCIAT","JPINFRATEC","JPOLYINVST","JPPOWER","JSL","JSWENERGY","JSWHL","JSWSTEEL","JSWSTEEL","JUBILANT","JUBLFOOD","JUBLINDS","JUNIORBEES","JUSTDIAL","JVLAGRO","JYOTHYLAB","JYOTISTRUC","KABRAEXTRU","KAJARIACER","KAKATCEM","KALINDEE","KALPATPOWR","KAMATHOTEL","KANANIIND","KANORICHEM","KANSAINER","KARMAENG","KARURVYSYA","KAUSHALYA","KAVVERITEL","KCP","KCPSUGIND","KEC","KECL","KEI","KEMROCK","KERNEX","KESARENT","KESORAMIND","KGL","KHAITANELE","KICL","KIL","KILITCH","KIRIINDUS","KIRLOSBROS","KIRLOSENG","KIRLOSIND","KITEX","KKCL","KMSUGAR","KNRCON","KOHINOOR","KOKUYOCMLN","KOLTEPATIL","KOPRAN","KOTAKBANK","KOTAKBKETF","KOTAKGOLD","KOTAKNIFTY","KOTAKPSUBK","KOTARISUG","KOTHARIPET","KOTHARIPRO","KPIT","KPRMILL","KRBL","KSBPUMPS","KSCL","KSERASERA","KSK","KSL","KTIL","KTKBANK","KWALITY","L&TFH","L&TFINANCE","L&TFINANCE","LAKPRE","LAKSHMIEFL","LAKSHVILAS","LAMBODHARA","LAOPALA","LAXMIMACH","LCCINFOTEC","LGBBROSLTD","LGBFORGE","LIBERTSHOE","LICHSGFIN","LICNMFET","LINCPEN","LINDEINDIA","LIQUIDBEES","LITL","LLOYDELENG","LML","LOKESHMACH","LOTUSEYE","LOVABLE","LPDC","LT","LUMAXAUTO","LUMAXIND","LUMAXTECH","LUPIN","LYCOS","LYKALABS","LYPSAGEMS","M&M","M&MFIN","M100","M50","MAANALU","MADHAV","MADHUCON","MADRASFERT","MAGMA","MAGNUM","MAHABANK","MAHINDCIE","MAHLIFE","MAHSCOOTER","MAHSEAMLES","MAITHANALL","MALUPAPER","MALWACOTT","MANAKALUCO","MANAKCOAT","MANAKINDST","MANAKSIA","MANAKSTEEL","MANALIPETC","MANAPPURAM","MANAPPURAM","MANDHANA","MANGALAM","MANGCHEFER","MANGLMCEM","MANGTIMBER","MANINDS","MANINFRA","MANPASAND","MANUGRAPH","MARALOVER","MARICO","MARKSANS","MARUTI","MASTEK","MAWANASUG","MAX","MAXWELL","MAYURUNIQ","MBECL","MBLINFRA","MCDHOLDING","MCDOWELL-N","MCLEODRUSS","MCX","MEGASOFT","MEGH","MENONBE","MEP","MERCATOR","MERCK","METALFORGE","METKORE","MHRIL","MIC","MICROSEC","MINDACORP","MINDAIND","MINDTREE","MIRCELECTR","MIRZAINT","MMFL","MMTC","MOHITIND","MOIL","MOLDTKPAC","MONNETISPA","MONSANTO","MONTECARLO","MORARJEE","MOREPENLAB","MOSERBAER","MOTHERSUMI","MOTILALOFS","MOTOGENFIN","MPHASIS","MPSLTD","MRF","MRO-TEK","MRPL","MSPL","MTEDUCARE","MTNL","MUKANDENGG","MUKANDLTD","MUKTAARTS","MUNJALAU","MUNJALSHOW","MURLIIND","MURUDCERA","MUTHOOTFIN","MUTHOOTFIN","MUTHOOTFIN","MVL","MYSOREBANK","N100","NAGAROIL","NAGREEKCAP","NAGREEKEXP","NAHARCAP","NAHARINDUS","NAHARPOLY","NAHARSPING","NAKODA","NATCOPHARM","NATHBIOGEN","NATIONALUM","NATNLSTEEL","NAUKRI","NAVINFLUOR","NAVNETEDUL","NBCC","NBVENTURES","NCC","NCLIND","NCOPPER","NDL","NDTV","NECLIFE","NELCAST","NELCO","NEPCMICON","NESCO","NESTLEIND","NETWORK18","NEULANDLAB","NEXTMEDIA","NEYVELILIG","NFL","NGCT","NHAI","NHAI","NHAI","NHAI","NHBTF2023","NHPC","NHPC","NIBL","NIFTYBEES","NIITLTD","NIITTECH","NILAINFRA","NILKAMAL","NIPPOBATRY","NIRVIKARA","NITCO","NITESHEST","NITINFIRE","NITINSPIN","NMDC","NOCIL","NOIDATOLL","NORBTEAEXP","NRBBEARING","NSIL","NTPC","NTPC","NUCLEUS","NUTEK","OBEROIRLTY","OCL","OFSS","OIL","OILCOUNTUB","OISL","OMAXAUTO","OMAXE","OMKARCHEM","OMMETALS","OMNITECH","ONELIFECAP","ONGC","ONMOBILE","ONWARDTEC","OPTOCIRCUI","ORBITCORP","ORBTEXP","ORCHIDCHEM","ORICONENT","ORIENTABRA","ORIENTALTL","ORIENTBANK","ORIENTBELL","ORIENTCEM","ORIENTHOT","ORIENTLTD","ORIENTPPR","ORIENTREF","ORISSAMINE","ORTEL","OUDHSUG","PAEL","PAGEIND","PANACEABIO","PANAMAPET","PANORAMUNI","PAPERPROD","PARABDRUGS","PARACABLES","PARASPETRO","PARRYSUGAR","PARSVNATH","PATELENG","PATINTLOG","PATSPINLTD","PBAINFRA","PCJEWELLER","PDPL","PDSMFL","PDUMJEIND","PDUMJEPULP","PEARLPOLY","PEL","PENIND","PENINLAND","PERSISTENT","PETRONENGG","PETRONET","PFC","PFIZER","PFOCUS","PFRL","PFS","PGEL","PGHH","PGIL","PHILIPCARB","PHOENIXLL","PHOENIXLTD","PIDILITIND","PIIND","PILIND","PIONDIST","PIONEEREMB","PIPAVAVDOC","PIRPHYTO","PITTILAM","PLASTIBLEN","PLETHICO","PNB","PNBGILTS","PNCINFRA","POCHIRAJU","POLARIS","POLYMED","POLYPLEX","PONNIERODE","POWERGRID","POWERGRID","PPAP","PRADIP","PRAENG","PRAJIND","PRAKASH","PRAKASHCON","PRAKASHSTL","PRATIBHA","PRECOT","PRECWIRE","PREMIER","PRESSMN","PRESTIGE","PRICOL","PRIMESECU","PRISMCEM","PROVOGE","PROZONINTU","PSB","PSL","PSUBNKBEES","PTC","PTL","PUNJABCHEM","PUNJLLOYD","PURVA","PVP","PVR","QGOLDHALF","QNIFTY","RADAAN","RADICO","RAIN","RAINBOWPAP","RAIREKMOH","RAJESHEXPO","RAJOIL","RAJRAYON","RAJSREESUG","RAJTV","RAJVIR","RALLIS","RAMANEWS","RAMCOCEM","RAMCOIND","RAMCOSYS","RAMKY","RAMSARUP","RANASUG","RANEENGINE","RANEHOLDIN","RASOYPR","RATNAMANI","RAYMOND","RBL","RCF","RCOM","RECLTD","RECLTD","RECLTD","RECLTD","RECLTD","RECLTD","REDINGTON","REFEX","REIAGROLTD","REISIXTEN","RELAXO","RELBANK","RELCAPITAL","RELCNX100","RELCONS","RELDIVOPP","RELGOLD","RELGRNIFTY","RELIANCE","RELIFIN","RELIFIN","RELIGARE","RELIGAREGO","RELINFRA","RELNIFTY","RELNV20","REMSONSIND","RENUKA","REPCOHOME","REPRO","RESPONIND","REVATHI","RICOAUTO","RIIL","RJL","RKDL","RKFORGE","RMCL","RML","RMMIL","ROHITFERRO","ROHLTD","ROLTA","ROSSELLIND","RPGLIFE","RPOWER","RPPINFRA","RSSOFTWARE","RSWM","RSYSTEMS","RTNINFRA","RTNPOWER","RUBYMILLS","RUCHINFRA","RUCHIRA","RUCHISOYA","RUPA","RUSHIL","SABTN","SADBHAV","SAGCEM","SAIL","SAKHTISUG","SAKSOFT","SAKUMA","SALORAINTL","SALSTEEL","SAMBHAAV","SANDESH","SANGAMIND","SANGHIIND","SANGHVIFOR","SANGHVIMOV","SANOFI","SANWARIA","SARDAEN","SAREGAMA","SARLAPOLY","SASKEN","SATHAISPAT","SBBJ","SBIGETS","SBIN","SBIN","SBIN","SBIN","SBIN","SBT","SCHNEIDER","SCI","SDBL","SEAMECLTD","SEINV","SELAN","SELMCL","SEPOWER","SERVALL","SESHAPAPER","SETFBANK","SETFNIFJR","SETFNIFTY","SFCL","SGJHL","SGL","SHAKTIPUMP","SHALPAINTS","SHANTIGEAR","SHARDACROP","SHARONBIO","SHASUNPHAR","SHEMAROO","SHILPAMED","SHILPI","SHIRPUR-G","SHIV-VANI","SHIVAMAUTO","SHIVTEX","SHLAKSHMI","SHOPERSTOP","SHREECEM","SHREERAMA","SHRENUJ","SHREYANIND","SHREYAS","SHRIASTER","SHRIRAMCIT","SHRIRAMCIT","SHRIRAMCIT","SHRIRAMEPC","SHYAMCENT","SHYAMTEL","SICAGEN","SICAL","SIEMENS","SIGNET","SIIL","SIL","SIMBHSUGAR","SIMPLEX","SIMPLEXINF","SINTEX","SITASHREE","SITICABLE","SIYSIL","SJVN","SKFINDIA","SKIL","SKIPPER","SKMEGGPROD","SKSMICRO","SMARTLINK","SMLISUZU","SMPL","SMSPHARMA","SNOWMAN","SOBHA","SOFTTECHGR","SOLARINDS","SOMANYCERA","SOMATEX","SONASTEER","SONATSOFTW","SOTL","SOUTHBANK","SPARC","SPECIALITY","SPENTEX","SPIC","SPICEMOBI","SPLIL","SPMLINFRA","SPYL","SQSBFSI","SREEL","SREIBNPNCD","SREIBNPNCD","SREINFRA","SRF","SRGINFOTEC","SRHHYPOLTD","SRIPIPES","SRSLTD","SRTRANSFIN","SRTRANSFIN","SRTRANSFIN","SRTRANSFIN","SRTRANSFIN","SRTRANSFIN","SRTRANSFIN","SRTRANSFIN","SRTRANSFIN","SRTRANSFIN","SRTRANSFIN","SRTRANSFIN","SRTRANSFIN","SRTRANSFIN","SRTRANSFIN","SRTRANSFIN","SSWL","STAN","STAR","STARPAPER","STCINDIA","STEL","STERLINBIO","STERTOOLS","STINDIA","STOREONE","STRTECH","STYABS","SUBEX","SUBROS","SUDAR","SUDARSCHEM","SUJANATWR","SUJANAUNI","SUMEETINDS","SUMMITSEC","SUNCLAYLTD","SUNDARAM","SUNDARMFIN","SUNDRMBRAK","SUNDRMFAST","SUNFLAG","SUNILHITEC","SUNPHARMA","SUNTECK","SUNTV","SUPERHOUSE","SUPERSPIN","SUPPETRO","SUPRAJIT","SUPREMEIND","SUPREMEINF","SUPREMETEX","SURANACORP","SURANAIND","SURANASOL","SURANAT&P","SURYAJYOTI","SURYALAXMI","SURYAROSNI","SUTLEJTEX","SUVEN","SUZLON","SWANENERGY","SWARAJENG","SWELECTES","SYMPHONY","SYNCOM","SYNDIBANK","SYNGENE","TAINWALCHM","TAJGVK","TAKE","TALBROAUTO","TALWALKARS","TANLA","TANTIACONS","TARAJEWELS","TARAPUR","TARMAT","TATACHEM","TATACOFFEE","TATACOMM","TATAELXSI","TATAGLOBAL","TATAINVEST","TATAMETALI","TATAMOTORS","TATAMTRDVR","TATAPOWER","TATASPONGE","TATASTEEL","TBZ","TCI","TCIDEVELOP","TCIFINANCE","TCS","TDPOWERSYS","TECHM","TECHNO","TECHNOFAB","TECPRO","TEXINFRA","TEXMOPIPES","TEXRAIL","TFCILTD","TFL","TGBHOTELS","THANGAMAYL","THEMISMED","THERMAX","THIRUSUGAR","THOMASCOOK","THOMASCOTT","TI","TIDEWATER","TIIL","TIL","TIMBOR","TIMESGTY","TIMETECHNO","TIMKEN","TINPLATE","TIPSINDLTD","TIRUMALCHM","TITAN","TNPETRO","TNPL","TNTELE","TODAYS","TOKYOPLAST","TORNTPHARM","TORNTPOWER","TPLPLASTEH","TREEHOUSE","TRENT","TRF","TRICOM","TRIDENT","TRIGYN","TRIL","TRITURBINE","TRIVENI","TTKPRESTIG","TTL","TTML","TUBEINVEST","TULSI","TV18BRDCST","TVSELECT","TVSMOTOR","TVSSRICHAK","TVTODAY","TWL","UBHOLDINGS","UBL","UCALFUEL","UCOBANK","UFLEX","UFO","UGARSUGAR","UJAAS","ULTRACEMCO","UMESLTD","UNICHEMLAB","UNIENTER","UNIONBANK","UNIPLY","UNITECH","UNITEDBNK","UNITEDTEA","UNITY","UNIVCABLES","UPERGANGES","UPL","USHAMART","USHERAGRO","UTTAMSTL","UTTAMSUGAR","UTTAMVALUE","V2RETAIL","VADILALIND","VAIBHAVGBL","VAKRANGEE","VALECHAENG","VALUEIND","VARDHACRLC","VARDMNPOLY","VASCONEQ","VASWANI","VEDL","VENKEYS","VENUSREM","VESUVIUS","VETO","VGUARD","VHL","VICEROY","VIDEOIND","VIJAYABANK","VIJSHAN","VIKASGLOB","VIMALOIL","VIMTALABS","VINATIORGA","VINDHYATEL","VINYLINDIA","VIPIND","VIPULLTD","VISAKAIND","VISASTEEL","VISESHINFO","VISHNU","VISUINTL","VIVIDHA","VIVIMEDLAB","VKSPL","VLSFINANCE","VMART","VOLTAMP","VOLTAS","VRLLOG","VSSL","VSTIND","VSTTILLERS","VTL","WABAG","WABCOINDIA","WALCHANNAG","WANBURY","WEBELSOLAR","WEIZFOREX","WEIZMANIND","WELCORP","WELENT","WELINV","WELSPUNIND","WENDT","WHEELS","WHIRLPOOL","WILLAMAGOR","WINDMACHIN","WINSOME","WIPRO","WOCKPHARMA","WONDERLA","WSI","WSTCSTPAPR","XCHANGING","XLENERGY","XPROINDIA","YESBANK","ZANDUREALT","ZEEL","ZEEL","ZEELEARN","ZEEMEDIA","ZENITHBIR","ZENITHCOMP","ZENITHEXPO","ZENSARTECH","ZENTEC","ZICOM","ZODIACLOTH","ZODJRDMKJ","ZUARI","ZUARIGLOB","ZYDUSWELL","ZYLOG"};
    ListView lv;
    int buttonId;
    Button b;
    TextView x;
    WebView wv,wvzoom;
    String url;
    String sname;
    public Trade() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_trade, container, false);
        ed3=(EditText)rootView.findViewById(R.id.editText3);
        ed4=(EditText)rootView.findViewById(R.id.editText4);
        name=(AutoCompleteTextView)rootView.findViewById(R.id.autoCompleteTextView);
        spinner = (ProgressBar)rootView.findViewById(R.id.pBar);
        spinner.setVisibility(View.GONE);
        b=(Button)rootView.findViewById(R.id.button4);
        wv= (WebView) rootView.findViewById(R.id.wv);
        wv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context1);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle(sname);
                spinnerzoom = (ProgressBar)dialog.findViewById(R.id.pBarzoom);
                spinnerzoom.setVisibility(View.VISIBLE);
                wvzoom= (WebView) dialog.findViewById(R.id.wvzoom);
                wvzoom.loadUrl(url);
                wvzoom.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        wvzoom.scrollTo(330, 750);
                        spinnerzoom.setVisibility(View.GONE);

                    }

                });
                x= (TextView) dialog.findViewById(R.id.tvx);
                x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            dialog.show();
            }
        });

        ArrayAdapter<String> symbols=new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.listitem,stocks);

        name.setThreshold(2);
        name.setAdapter(symbols);

        name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sname = name.getText().toString();
                Log.d("Position", "" + position);
                Log.d("id", "" + id);
                spinner.setVisibility(View.VISIBLE);
                url = "https://in.finance.yahoo.com/q?s=" + sname + "&ql=1";
                wv.loadUrl(url);
                wv.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        wv.scrollTo(330, 750);
                        spinner.setVisibility(View.GONE);
                    }
                });


            }
        });

        stockGroup=(RadioGroup)rootView.findViewById(R.id.radioGroup);
        name.setTextColor(Color.BLACK);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                buttonId = stockGroup.getCheckedRadioButtonId();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity().getApplicationContext(), "id -> "+buttonId, Toast.LENGTH_LONG).show();
                    }
                });

                Log.d("tag","id -> "+buttonId);

                String scid = Global.read(getActivity().getApplicationContext());

                final String sname, quantity, price;
                final int cid = Integer.parseInt(scid);
                final Connection con = Global.conn;

                sname = name.getText().toString();
                quantity = ed3.getText().toString();
                final int quantity1 = Integer.parseInt(quantity);
                price = ed4.getText().toString();
                final double price1 = Double.parseDouble(price);


                if (buttonId == R.id.buybutton) {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                double balance;

                                Statement st3 = Global.getInstance().createStatement();
                                String s3 = "select balance from cbalance where cid=" + cid;
                                ResultSet rs3 = st3.executeQuery(s3);
                                if (rs3.next()) {
                                    balance = rs3.getDouble(1);
                                } else {
                                    balance = -1;
                                }
                                if ((balance - price1 * quantity1) < 0) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getActivity().getApplicationContext(), "Order Price Exceeds Your Balance", Toast.LENGTH_LONG).show();
                                        }
                                    });

                                } else {
                                    Statement st = Global.getInstance().createStatement();
                                    String s = "insert into tobuy values('" + sname + "'," + cid + "," + price1 + "," + quantity + ",'pending')";
                                    st.executeUpdate(s);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getActivity().getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                                        }
                                    });

                                }


                            } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                Log.e("Submit error", e.toString());
                            }

                        }
                    });
                    t.start();

                } else if (buttonId == R.id.sellbutton) {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {


                                Statement st = con.createStatement();
                                String s = "insert into tosell values('" + sname + "'," + cid + "," + price1 + "," + quantity + ",'pending')";
                                st.executeUpdate(s);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity().getApplicationContext(), "Success!!!", Toast.LENGTH_LONG).show();
                                    }
                                });

                            } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                Log.e("Submit error", e.toString());
                            }

                        }
                    });
                    t.start();


                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity().getApplicationContext(), "Choose an option to buy or sell", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });
        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
