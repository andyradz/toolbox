package com.codigo.aplios.toolbox.core.attributes;

public enum CodeLevel {

	DEVELOP,
	PRE_ALPHA,
	PRE_BETA,
	BETA,
	RELEASE_CANDIDATE,
	RTM
}

/*
 * wersja niestabilna (testowa) – seria wydań, podczas której dodawane są przede wszystkim nowe możliwości: wersja
 * robocza (pre-alpha) – dostępna zazwyczaj tylko dla twórców programu w postaci repozytorium kodu źródłowego (np. CVS,
 * SVN, GIT), kiedy implementowany jest algorytm programu, tworzony jest interfejs i dodawane są nowe funkcje; wersja
 * alfa (pre-beta) – autorzy doprowadzają do rzeczywistego działania programu, nawet w ograniczonym zakresie; wersja
 * beta – kiedy program ma już pierwszych użytkowników, zwanych często beta testerami, wyłapywane są błędy związane z
 * różnymi środowiskami i warunkami pracy programu RC (ang. Release Candidate, czyli kandydat do wydania) – wydanie
 * kandydujące, których może być nawet kilka, ale jeżeli nie zostanie w nim znalezione żadne istotne odstępstwo od planu
 * wersji, zmienia się jedynie numer wersji na wyższy i uznaje wersję za stabilną.
 *
 * wersja stabilna (wersja produkcyjna) – wersja nadająca się do użytkowania zgodnie z założeniami autorów RTM (ang.
 * Release To Manufacture, Ready To Manufacture lub Ready To Market czyli gotowy do wydania) – produkt uznany za
 * stabilny i gotowy do wypuszczenia na rynek; nie jest dostępny publicznie do czasu premiery; wersje stabilne z
 * poprawkami bezpieczeństwa lub innych błędów.
 *
 * starzenie moralne programu – zwykle ostatni etap polegający na porzuceniu programu przez autorów, co zwykle kończy
 * jego życie; w przypadku kodu na licencjach FLOSS ten stan może w dowolnym momencie ponownie przejść do fazy aktywnego
 * rozwoju, jeśli tylko znajdą się chętni do przejęcia opieki nad nim lub wykorzystają fragmenty kodu w innej aplikacji.
 *
 * Zależnie od projektu niektóre fazy życia programu mogą nie być tak oznaczane, można natomiast mówić w tych
 * kategoriach o jakości kodu (np. kod beta oznacza wtedy po prostu program, w którym zaniedbano testowania na
 * rozmaitych platformach sprzętowych i programistycznych).
 */