package net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.lang.RuntimeException;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.CurrencyTypeDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.CurrencyType;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.dtogen.Dto2CurrencyTypeGenerator;

/**
 * Dto2PosoGenerator for CurrencyType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CurrencyTypeGenerator implements Dto2PosoGenerator<CurrencyTypeDto,CurrencyType> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2CurrencyTypeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CurrencyType loadPoso(CurrencyTypeDto dto)  {
		return createPoso(dto);
	}

	public CurrencyType instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public CurrencyType createPoso(CurrencyTypeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case EURO:
				return CurrencyType.EURO;
			case DOLLAR:
				return CurrencyType.DOLLAR;
			case BRITTISH_POUND:
				return CurrencyType.BRITTISH_POUND;
			case AED:
				return CurrencyType.AED;
			case AFN:
				return CurrencyType.AFN;
			case ALL:
				return CurrencyType.ALL;
			case AMD:
				return CurrencyType.AMD;
			case ANG:
				return CurrencyType.ANG;
			case AOA:
				return CurrencyType.AOA;
			case ARS:
				return CurrencyType.ARS;
			case AUD:
				return CurrencyType.AUD;
			case AWG:
				return CurrencyType.AWG;
			case AZN:
				return CurrencyType.AZN;
			case BAM:
				return CurrencyType.BAM;
			case BBD:
				return CurrencyType.BBD;
			case BDT:
				return CurrencyType.BDT;
			case BGN:
				return CurrencyType.BGN;
			case BHD:
				return CurrencyType.BHD;
			case BIF:
				return CurrencyType.BIF;
			case BMD:
				return CurrencyType.BMD;
			case BND:
				return CurrencyType.BND;
			case BOB:
				return CurrencyType.BOB;
			case BRL:
				return CurrencyType.BRL;
			case BSD:
				return CurrencyType.BSD;
			case BTN:
				return CurrencyType.BTN;
			case BWP:
				return CurrencyType.BWP;
			case BYR:
				return CurrencyType.BYR;
			case BZD:
				return CurrencyType.BZD;
			case CAD:
				return CurrencyType.CAD;
			case CDF:
				return CurrencyType.CDF;
			case CHF:
				return CurrencyType.CHF;
			case CLP:
				return CurrencyType.CLP;
			case CNY:
				return CurrencyType.CNY;
			case COP:
				return CurrencyType.COP;
			case CRC:
				return CurrencyType.CRC;
			case CUC:
				return CurrencyType.CUC;
			case CUP:
				return CurrencyType.CUP;
			case CVE:
				return CurrencyType.CVE;
			case CZK:
				return CurrencyType.CZK;
			case DJF:
				return CurrencyType.DJF;
			case DKK:
				return CurrencyType.DKK;
			case DOP:
				return CurrencyType.DOP;
			case DZD:
				return CurrencyType.DZD;
			case EGP:
				return CurrencyType.EGP;
			case ERN:
				return CurrencyType.ERN;
			case ETB:
				return CurrencyType.ETB;
			case EUR:
				return CurrencyType.EUR;
			case FJD:
				return CurrencyType.FJD;
			case FKP:
				return CurrencyType.FKP;
			case GBP:
				return CurrencyType.GBP;
			case GEL:
				return CurrencyType.GEL;
			case GGP:
				return CurrencyType.GGP;
			case GHS:
				return CurrencyType.GHS;
			case GIP:
				return CurrencyType.GIP;
			case GMD:
				return CurrencyType.GMD;
			case GNF:
				return CurrencyType.GNF;
			case GTQ:
				return CurrencyType.GTQ;
			case GYD:
				return CurrencyType.GYD;
			case HKD:
				return CurrencyType.HKD;
			case HNL:
				return CurrencyType.HNL;
			case HRK:
				return CurrencyType.HRK;
			case HTG:
				return CurrencyType.HTG;
			case HUF:
				return CurrencyType.HUF;
			case IDR:
				return CurrencyType.IDR;
			case ILS:
				return CurrencyType.ILS;
			case IMP:
				return CurrencyType.IMP;
			case INR:
				return CurrencyType.INR;
			case IQD:
				return CurrencyType.IQD;
			case IRR:
				return CurrencyType.IRR;
			case ISK:
				return CurrencyType.ISK;
			case JEP:
				return CurrencyType.JEP;
			case JMD:
				return CurrencyType.JMD;
			case JOD:
				return CurrencyType.JOD;
			case JPY:
				return CurrencyType.JPY;
			case KES:
				return CurrencyType.KES;
			case KGS:
				return CurrencyType.KGS;
			case KHR:
				return CurrencyType.KHR;
			case KMF:
				return CurrencyType.KMF;
			case KPW:
				return CurrencyType.KPW;
			case KRW:
				return CurrencyType.KRW;
			case KWD:
				return CurrencyType.KWD;
			case KYD:
				return CurrencyType.KYD;
			case KZT:
				return CurrencyType.KZT;
			case LAK:
				return CurrencyType.LAK;
			case LBP:
				return CurrencyType.LBP;
			case LKR:
				return CurrencyType.LKR;
			case LRD:
				return CurrencyType.LRD;
			case LSL:
				return CurrencyType.LSL;
			case LYD:
				return CurrencyType.LYD;
			case MAD:
				return CurrencyType.MAD;
			case MDL:
				return CurrencyType.MDL;
			case MGA:
				return CurrencyType.MGA;
			case MKD:
				return CurrencyType.MKD;
			case MMK:
				return CurrencyType.MMK;
			case MNT:
				return CurrencyType.MNT;
			case MOP:
				return CurrencyType.MOP;
			case MRU:
				return CurrencyType.MRU;
			case MUR:
				return CurrencyType.MUR;
			case MVR:
				return CurrencyType.MVR;
			case MWK:
				return CurrencyType.MWK;
			case MXN:
				return CurrencyType.MXN;
			case MYR:
				return CurrencyType.MYR;
			case MZN:
				return CurrencyType.MZN;
			case NAD:
				return CurrencyType.NAD;
			case NGN:
				return CurrencyType.NGN;
			case NIO:
				return CurrencyType.NIO;
			case NOK:
				return CurrencyType.NOK;
			case NPR:
				return CurrencyType.NPR;
			case NZD:
				return CurrencyType.NZD;
			case OMR:
				return CurrencyType.OMR;
			case PAB:
				return CurrencyType.PAB;
			case PEN:
				return CurrencyType.PEN;
			case PGK:
				return CurrencyType.PGK;
			case PHP:
				return CurrencyType.PHP;
			case PKR:
				return CurrencyType.PKR;
			case PLN:
				return CurrencyType.PLN;
			case PYG:
				return CurrencyType.PYG;
			case QAR:
				return CurrencyType.QAR;
			case RON:
				return CurrencyType.RON;
			case RSD:
				return CurrencyType.RSD;
			case RUB:
				return CurrencyType.RUB;
			case RWF:
				return CurrencyType.RWF;
			case SAR:
				return CurrencyType.SAR;
			case SBD:
				return CurrencyType.SBD;
			case SCR:
				return CurrencyType.SCR;
			case SDG:
				return CurrencyType.SDG;
			case SEK:
				return CurrencyType.SEK;
			case SGD:
				return CurrencyType.SGD;
			case SHP:
				return CurrencyType.SHP;
			case SLL:
				return CurrencyType.SLL;
			case SOS:
				return CurrencyType.SOS;
			case SRD:
				return CurrencyType.SRD;
			case SSP:
				return CurrencyType.SSP;
			case STN:
				return CurrencyType.STN;
			case SVC:
				return CurrencyType.SVC;
			case SYP:
				return CurrencyType.SYP;
			case SZL:
				return CurrencyType.SZL;
			case THB:
				return CurrencyType.THB;
			case TJS:
				return CurrencyType.TJS;
			case TMT:
				return CurrencyType.TMT;
			case TND:
				return CurrencyType.TND;
			case TOP:
				return CurrencyType.TOP;
			case TRY:
				return CurrencyType.TRY;
			case TTD:
				return CurrencyType.TTD;
			case TVD:
				return CurrencyType.TVD;
			case TWD:
				return CurrencyType.TWD;
			case TZS:
				return CurrencyType.TZS;
			case UAH:
				return CurrencyType.UAH;
			case UGX:
				return CurrencyType.UGX;
			case USD:
				return CurrencyType.USD;
			case UYU:
				return CurrencyType.UYU;
			case UZS:
				return CurrencyType.UZS;
			case VEF:
				return CurrencyType.VEF;
			case VND:
				return CurrencyType.VND;
			case VUV:
				return CurrencyType.VUV;
			case WST:
				return CurrencyType.WST;
			case XAF:
				return CurrencyType.XAF;
			case XCD:
				return CurrencyType.XCD;
			case XOF:
				return CurrencyType.XOF;
			case XPF:
				return CurrencyType.XPF;
			case YER:
				return CurrencyType.YER;
			case ZAR:
				return CurrencyType.ZAR;
			case ZMW:
				return CurrencyType.ZMW;
			case ZWL:
				return CurrencyType.ZWL;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public CurrencyType createUnmanagedPoso(CurrencyTypeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(CurrencyTypeDto dto, CurrencyType poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(CurrencyTypeDto dto, CurrencyType poso)  {
		/* no merging for enums */
	}

	public CurrencyType loadAndMergePoso(CurrencyTypeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(CurrencyTypeDto dto, CurrencyType poso)  {
	}


	public void postProcessCreateUnmanaged(CurrencyTypeDto dto, CurrencyType poso)  {
	}


	public void postProcessLoad(CurrencyTypeDto dto, CurrencyType poso)  {
	}


	public void postProcessMerge(CurrencyTypeDto dto, CurrencyType poso)  {
	}


	public void postProcessInstantiate(CurrencyType poso)  {
	}



}
