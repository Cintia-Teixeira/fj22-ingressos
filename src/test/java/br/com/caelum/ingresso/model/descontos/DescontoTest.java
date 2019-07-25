package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

public class DescontoTest {

	private Sala sala;
	private Filme filme;
	private Sessao sessao;
	/*private Ingresso ingressoSemDesconto;
	private Ingresso descontoParaBancos;
	private Ingresso descontoParaEstudantes;*/
	private Ingresso ingressoInteiro;
	private Ingresso ingressoEstudante;
	private Ingresso ingressoBanco;
	private Lugar lugar;
	private Desconto desconto;
	
	
	@Before
	public void preparaDescontos() {

		this.sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
		this.filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		this.sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
		/*this.ingressoSemDesconto = new Ingresso(sessao, new SemDesconto());
		this.descontoParaBancos = new Ingresso(sessao, new DescontoParaBancos());
		this.descontoParaEstudantes = new Ingresso(sessao, new DescontoParaEstudantes());*/
		this.lugar = new Lugar("A", 1);
		this.ingressoInteiro = new Ingresso(sessao, TipoDeIngresso.INTEIRO, lugar);
		this.ingressoEstudante = new Ingresso(sessao, TipoDeIngresso.ESTUDANTE, lugar);
		this.ingressoBanco = new Ingresso(sessao, TipoDeIngresso.BANCO, lugar);
	
	}

	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {

		BigDecimal precoEsperado = new BigDecimal("32.50");

		Assert.assertEquals(precoEsperado, this.ingressoInteiro.getPreco());
	}

	@Test
	public void deveConcederDescontoDe30PorCentoParaClientesDeBancos() {

		BigDecimal precoEsperado = new BigDecimal("22.75");

		Assert.assertEquals(precoEsperado, this.ingressoBanco.getPreco());
	}

	@Test
	public void deveConcederDescontoDe50PorCentoParaEstudantes() {

		BigDecimal precoEsperado = new BigDecimal("16.25");

		Assert.assertEquals(precoEsperado, this.ingressoEstudante.getPreco());
	}

}
