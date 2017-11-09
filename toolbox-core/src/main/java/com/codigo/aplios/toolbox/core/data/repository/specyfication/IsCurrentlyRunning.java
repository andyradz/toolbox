package com.codigo.aplios.toolbox.core.data.repository.specyfication;

// public class IsCurrentlyRunning extends AbstractSpecification<T> {
//
// @Override
// public boolean isSatisfiedBy(Poll poll) {
//
// return poll.getStartDate().isBeforeNow() && poll.getEndDate().isAfterNow() && poll.getLockDate() == null;
// }
//
// @Override
// public Predicate toPredicate(Root<Poll> poll, CriteriaBuilder cb) {
//
// DateTime now = new DateTime();
// return cb.and(cb.lessThan(poll.get(Poll_.startDate), now), cb.greaterThan(poll.get(Poll_.endDate), now), cb
// .isNull(poll.get(Poll_.lockDate)));
// }
// }
