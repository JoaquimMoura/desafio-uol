package com.solo.domain.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solo.domain.exception.BusinessException;
import com.solo.domain.model.Plantao;
import com.solo.repository.UolRespository;

@Service
public class UolService {

	@Autowired UolRespository respository;

	public List<Plantao> findListPrantao() {

		final Set<Plantao> newListPlantao = new HashSet<Plantao>();

		final List<Plantao> listPlantao = respository.findByOrderByDataPlantaoAsc();

		for (final Plantao plantao : listPlantao) {

			try {

				final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				final SimpleDateFormat formatador = new SimpleDateFormat("(dd/MM)");

				final String dataAtual = sdf.format(new Date());

				final Date newDataAtual = sdf.parse(dataAtual);

				final Date datePlantao = sdf.parse(plantao.getDataPlantao());

				final Date anteOntem = DateUtils.addDays(new Date(), -2);

				final String newDate = sdf.format(anteOntem);

				final Date newAnteOntem = sdf.parse(newDate);

				final Date depoisAmanha = DateUtils.addDays(new Date(), +2);

				final String newDate1 = sdf.format(depoisAmanha);

				final Date newdepoisAmanha = sdf.parse(newDate1);

				if ((datePlantao.before(newDataAtual) && datePlantao.after(newAnteOntem))
						&& !datePlantao.equals(newDataAtual)) {

					final String diaOntem = formatador.format(datePlantao);
					plantao.setDataPlantao("Ontem" + diaOntem);
					newListPlantao.add(plantao);

				} else if (datePlantao.equals(newDataAtual)) {

					final String diaAtual = formatador.format(datePlantao);
					plantao.setDataPlantao("Hoje" + diaAtual);

				} else if ((datePlantao.after(newDataAtual) && datePlantao.before(newdepoisAmanha))
						&& !datePlantao.equals(newDataAtual)) {

					final String diaDepoisDeAmanha = formatador.format(datePlantao);
					plantao.setDataPlantao("Amanhã" + diaDepoisDeAmanha);

				} else {

					final Calendar cal = Calendar.getInstance();
					cal.setTime(datePlantao);
					final int diaSemana = cal.get(Calendar.DAY_OF_WEEK);

					final String dia = pesquisarDiaSemana(diaSemana);

					plantao.setDataPlantao(dia + formatador.format(datePlantao));
				}

			} catch (Exception e) {
				throw new BusinessException("Erro, identifique sua merda");
			}
		}
		;

		return listPlantao;

	}

	public Plantao savePrantao(Plantao plantao) {
		return respository.save(plantao);
	}

	// faz a pesquisa, dado um inteiro de 1 a 7
	public String pesquisarDiaSemana(int _diaSemana) {
		String diaSemana = null;

		switch (_diaSemana) {

		case 1: {
			diaSemana = "Domingo";
			break;
		}
		case 2: {
			diaSemana = "Segunda";
			break;
		}
		case 3: {
			diaSemana = "Terça";
			break;
		}
		case 4: {
			diaSemana = "Quarta";
			break;
		}
		case 5: {
			diaSemana = "Quinta";
			break;
		}
		case 6: {
			diaSemana = "Sexta";
			break;
		}
		case 7: {
			diaSemana = "Sábado";
			break;
		}

		}
		return diaSemana;

	}
}
